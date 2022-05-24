package cn.edu.bjtu.brilley.controller;

import cn.edu.bjtu.brilley.common.SuccessMessage;
import cn.edu.bjtu.brilley.common.WarningMessage;
import cn.edu.bjtu.brilley.domain.Sessions;
import cn.edu.bjtu.brilley.domain.SongList;
import cn.edu.bjtu.brilley.service.SessionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Brilley
 * @date 2022/5/20
 */

@RestController
public class SessionsController {

    @Autowired
    private SessionsService sessionsService;

    //返回用户的所有对话列表
    @RequestMapping(value = "/sessions/get_session_list", method = RequestMethod.GET)
    public Object getSessionList(HttpServletRequest req) {
        //服务器中断后，客户端如果不重连，重新的session里是没有userId的,这里改进去掉使用session,为了安全起见
        String userIdStr = req.getParameter("userId").trim();
        Pattern pattern = Pattern.compile("^\\d{1,9}$");
        if (!pattern.matcher(userIdStr).matches())
            return new WarningMessage("请求参数userId不合法，获取聊天记录失败").getMessage();

        Integer userId = new Integer(userIdStr);
        String lastTime = req.getParameter("lastTime").trim();
        System.out.println("param lastTime is: " + lastTime + " ---  param userId is: " + userId);
        if (lastTime.equals(""))
            lastTime = String.valueOf(System.currentTimeMillis());

        return new SuccessMessage<List<Sessions>>(null, sessionsService.userSessions(userId,lastTime)).getMessage();
    }

    //返回用户的所有对话列表
    @RequestMapping(value = "/sessions/reset_unread", method = RequestMethod.GET)
    public Object resetUnread(HttpServletRequest req, HttpSession session) {
//        String lastTime = req.getParameter("lastTime").trim();
//        String userId = session.getAttribute("userId").toString();
//        System.out.println(userId);
        return "success";
        //return new SuccessMessage<List<SongList>>(null, songListService.allSongList()).getMessage();
    }
}
