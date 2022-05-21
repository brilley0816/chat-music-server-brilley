package cn.edu.bjtu.brilley.controller;

import cn.edu.bjtu.brilley.common.SuccessMessage;
import cn.edu.bjtu.brilley.common.WarningMessage;
import cn.edu.bjtu.brilley.domain.Chats;
import cn.edu.bjtu.brilley.service.ChatService;
import cn.edu.bjtu.brilley.service.SessionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Brilley
 * @date 2022/5/21
 */
@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @RequestMapping(value = "/chats/get_chat_list", method = RequestMethod.GET)
    public Object getChatList(HttpServletRequest req, HttpSession session) {
        String lastTime = req.getParameter("lastTime").trim();
        String sessionId = req.getParameter("sessionId").trim();
        if (lastTime != null && sessionId != null)
            return new SuccessMessage<List<Chats>>(null, chatService.getChatList(sessionId, lastTime)).getMessage();
        return new WarningMessage("获取聊天记录失败").getMessage();
    }
}
