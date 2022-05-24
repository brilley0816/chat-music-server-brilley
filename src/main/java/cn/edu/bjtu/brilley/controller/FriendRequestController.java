package cn.edu.bjtu.brilley.controller;

import cn.edu.bjtu.brilley.common.SuccessMessage;
import cn.edu.bjtu.brilley.common.WarningMessage;
import cn.edu.bjtu.brilley.domain.Consumer;
import cn.edu.bjtu.brilley.domain.FriendRequests;
import cn.edu.bjtu.brilley.domain.Sessions;
import cn.edu.bjtu.brilley.service.ConsumerService;
import cn.edu.bjtu.brilley.service.FriendRequestService;
import cn.edu.bjtu.brilley.service.FriendService;
import cn.edu.bjtu.brilley.service.impl.ConsumerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Brilley
 * @date 2022/5/22
 */
@RestController
public class FriendRequestController {

    @Autowired
    private FriendRequestService friendRequestService;

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private FriendService friendService;
    /**
     * 获取好友请求列表
     * @param req
     * @param session
     * @return
     */
    @RequestMapping(value = "/friend_requests/get_friend_request_list", method = RequestMethod.GET)
    public Object getFriendRequestList(HttpServletRequest req) {
        String userIdStr = req.getParameter("userId").trim();
        Pattern pattern = Pattern.compile("^\\d{1,9}$");
        if (!pattern.matcher(userIdStr).matches())
            return new WarningMessage("请求参数userId不合法，获取聊天记录失败").getMessage();
        Integer userId = new Integer(userIdStr);
        String lastTime = req.getParameter("lastTime").trim();
        System.out.println("param lastTime is: " + lastTime + " ---  param userId is: " + userId);
        if (lastTime.equals(""))
            lastTime = String.valueOf(System.currentTimeMillis());

        return new SuccessMessage<List<FriendRequests>>(null, friendRequestService.getFriendRequestList(userId,lastTime)).getMessage();
    }

    /**
     * 添加好友请求 到数据库
     * @param req
     * @param session
     * @return
     */
    @RequestMapping(value = "/friend_requests/add_friend_request", method = RequestMethod.GET)
    public Object addFriendRequest(HttpServletRequest req, HttpSession session) {

        Pattern pattern = Pattern.compile("^\\d{1,9}$");

        String userIdStr = req.getParameter("userId").trim();
        if (!pattern.matcher(userIdStr).matches()) {
            return new WarningMessage("请求参数userId不合法，获取聊天记录失败").getMessage();
        }

        Integer userId = new Integer(userIdStr);

        String friendIdStr = req.getParameter("friendId").trim();
        if (!pattern.matcher(friendIdStr).matches()) {
            return new WarningMessage("请求参数friendId不合法，获取聊天记录失败").getMessage();
        }
        Integer friendId = new Integer(friendIdStr);

        String content = req.getParameter("content").trim();

        if (friendId.equals(userId)) {
            return new WarningMessage("添加好友不能是自己哟").getMessage();
        }

        if (!consumerService.existUserById(friendId)) {
            return new WarningMessage("该好友ID不存在哟").getMessage();
        }


        //判断是不是已经是好友，需要FriendService，待写
        if(friendService.getIsFriend(userId, friendId)) {
            return new WarningMessage("已经是好友了哟").getMessage();
        }


        //判断好友请求是不是已经存在，这里user和Friend需要反过来
        //这里改一下，isExistFriendRequest返回requestID,不为零说明存在
        //不可以直接用isExistFriend来返回ID，它只能用来判断数据库有没有这条数据，这个方法必须要有，直接选择返回，如果没有这个数据会发生很严重的错误

        if (friendRequestService.isExistFriendRequest(friendId, userId)) {
            FriendRequests friendRequest = new FriendRequests();
            friendRequest.setHandle(0);
            friendRequest.setMsgread(0);
            friendRequest.setContent(content);
            friendRequest.setCreatedat(String.valueOf(System.currentTimeMillis()));
            //按friendId, userId来update
            friendRequestService.updateFriendRequest(friendRequest);
            return new SuccessMessage("已更新添加请求", null).getMessage();
        }

        return new SuccessMessage("已发送添加请求", friendRequestService.addFriendRequest(friendId, userId,content)).getMessage();
    }
}
