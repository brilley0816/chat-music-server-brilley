package cn.edu.bjtu.brilley.controller;

import cn.edu.bjtu.brilley.common.SuccessMessage;
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
    public Object getSessionList(HttpServletRequest req, HttpSession session) {
        String lastTime = req.getParameter("lastTime").trim();
        Integer userId = new Integer(session.getAttribute("userId").toString());
        System.out.println(userId);
        return new SuccessMessage<List<Sessions>>(null, sessionsService.userSessions(userId,lastTime)).getMessage();
    }

    //返回用户的所有对话列表
    @RequestMapping(value = "/sessions/reset_unread", method = RequestMethod.GET)
    public Object resetUnread(HttpServletRequest req, HttpSession session) {
        String lastTime = req.getParameter("lastTime").trim();
        String userId = session.getAttribute("userId").toString();
        System.out.println(userId);
        return "success";
        //return new SuccessMessage<List<SongList>>(null, songListService.allSongList()).getMessage();
    }
}
