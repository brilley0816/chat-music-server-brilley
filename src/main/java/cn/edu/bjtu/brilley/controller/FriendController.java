package cn.edu.bjtu.brilley.controller;

import cn.edu.bjtu.brilley.common.ChatMessage;
import cn.edu.bjtu.brilley.common.SuccessMessage;
import cn.edu.bjtu.brilley.common.WarningMessage;
import cn.edu.bjtu.brilley.domain.*;
import cn.edu.bjtu.brilley.service.*;
import cn.edu.bjtu.brilley.socketio.MessageEventHandler;
import com.corundumstudio.socketio.SocketIOClient;
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
 * @date 2022/5/21
 */
@RestController
public class FriendController {

    @Autowired
    FriendService friendService;

    @Autowired
    FriendRequestService friendRequestService;

    @Autowired
    ConsumerService consumerService;

    @Autowired
    SessionsService sessionsService;

    @Autowired
    ChatService chatService;

    @RequestMapping(value = "/friends/add", method = RequestMethod.GET)
    public Object addFriend(HttpServletRequest req) {
        String requestIdStr = req.getParameter("requestId").trim();
        String userIdStr = req.getParameter("userId").trim();
        Pattern pattern = Pattern.compile("^\\d{1,9}$");
        if (!pattern.matcher(userIdStr).matches())
            return new WarningMessage("请求参数userId不合法，添加好友失败").getMessage();
        if (!pattern.matcher(requestIdStr).matches())
            return new WarningMessage("请求参数requestId不合法，添加好友失败").getMessage();

        Integer userId = new Integer(userIdStr);
        Integer requestId = new Integer(requestIdStr);

        //判断好友请求存不存在
        List<FriendRequests> requestsRets = friendRequestService.getFriendRequestById(requestId);
        if (requestsRets.size() == 0)
            return new WarningMessage("无此好友请求，添加好友失败").getMessage();

        //判断现在是不是好友
        FriendRequests temp = requestsRets.get(0);

        if (friendService.getIsFriend(userId, temp.getFriendid())) {
            return new WarningMessage("已经是好友了你们").getMessage();
        }

        Integer friendId = temp.getFriendid();

        boolean addResult = friendService.addFriend(userId, friendId);

        if (!addResult)
            return new WarningMessage("添加动作失败").getMessage();

        //更新FriendRequest
        FriendRequests friendRequests = new FriendRequests();
        friendRequests.setMsgread(1);
        friendRequests.setHandle(1);
        friendRequests.setCreatedat(String.valueOf(System.currentTimeMillis()));
        friendRequests.setUserid(userId);
        friendRequests.setFriendid(friendId);
        boolean updateRequestRet = friendRequestService.updateFriendRequest(friendRequests);

        if (!updateRequestRet)
            return new WarningMessage("更新请求失败").getMessage();

        //获取好友信息
        List<Friends> friendsInfo = friendService.getFriendInfoById(userId, friendId);

        String sessionIdStr;
        //判断会话是否存在,是则获取sessionId,否则新建sessionId,是创建两边的session!!!
        if (sessionsService.isSessionExist(userId, friendId)){
            sessionIdStr = sessionsService.getSessionId(userId, friendId);
        } else {
            sessionIdStr = sessionsService.createSessions(userId, friendId, temp.getContent(), String.valueOf(System.currentTimeMillis()),0);
        }
        System.out.println("3");
        SuccessMessage<List<Friends>> responseResult = new SuccessMessage<List<Friends>>(null, friendsInfo);

        //创建聊天记录
        Chats chats = new Chats();
        chats.setSessionid(sessionIdStr);
        chats.setSenderid(friendId);
        chats.setReceiverid(userId);
        chats.setContent(temp.getContent());
        chats.setType(0);
        chats.setUpdatedat(String.valueOf(System.currentTimeMillis()));
        if (!chatService.addChat(chats))
            System.out.println("聊天记录插入失败");
        System.out.println("4");

        //推送消息websocket,给自己
        if (MessageEventHandler.socketsClient.containsKey(userIdStr)) {
            ChatMessage chatMessage = new ChatMessage(temp.getContent(),String.valueOf(System.currentTimeMillis()),sessionIdStr, friendId, 0);
            SocketIOClient client = MessageEventHandler.socketsClient.get(userIdStr);
            client.sendEvent("chat", chatMessage.getMessage());
        }

        if (MessageEventHandler.socketsClient.containsKey(friendId.toString())) {
            ChatMessage chatMessage = new ChatMessage(temp.getContent(),String.valueOf(System.currentTimeMillis()),sessionIdStr, userId, 0);
            SocketIOClient client = MessageEventHandler.socketsClient.get(temp.getFriendid().toString());
            client.sendEvent("chat", chatMessage.getMessage());
        }
        System.out.println("5");
        return responseResult.getMessage();
    }

    @RequestMapping(value = "/friends/get_list", method = RequestMethod.GET)
    public Object getFriendList( HttpServletRequest req) {
        String userIdStr = req.getParameter("userId").trim();
        Pattern pattern = Pattern.compile("^\\d{1,9}$");
        if (!pattern.matcher(userIdStr).matches())
            return new WarningMessage("请求参数userId不合法，获取聊天记录失败").getMessage();
        Integer userId = new Integer(userIdStr);
        System.out.println("friendlist请求 Id: " + userId);
        System.out.println("请求friendList");
        if (MessageEventHandler.socketsClient.containsKey(userIdStr)) {
            SocketIOClient client = MessageEventHandler.socketsClient.get(userIdStr);
            System.out.println("发了brilley类消息");
            client.sendEvent("brilley", "hello,这是新的消息");
        }
        return new SuccessMessage<List<Friends>>(null, friendService.getFriendList(userId)).getMessage();
    }

    @RequestMapping(value = "/friends/delete", method = RequestMethod.GET)
    public Object deleteFriend(HttpServletRequest req, HttpSession session) {
        String lastTime = req.getParameter("lastTime").trim();
        Integer userId = new Integer(session.getAttribute("userId").toString());
        System.out.println(userId);
        return new SuccessMessage<>(null, null).getMessage();
    }
}
