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
import java.util.regex.Pattern;

/**
 * @author Brilley
 * @date 2022/5/21
 */
@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @RequestMapping(value = "/chats/get_chat_list", method = RequestMethod.GET)
    public Object getChatList(HttpServletRequest req) {
        System.out.println("请求chat_list");
        String lastTime = req.getParameter("lastTime").trim();
        String sessionId = req.getParameter("sessionId").trim();
        System.out.println("param lastTime is: " + lastTime + " ---  param sessionId is: " + sessionId);

        //如果sessionId为空，或者不全是数字，"^-?\\d{1,9}$", 长度一到九的数字
        Pattern pattern = Pattern.compile("^\\d{1,9}$");
        if (!pattern.matcher(sessionId).matches())
            return new WarningMessage("请求参数sessionId不合法，获取聊天记录失败").getMessage();

        if (lastTime.equals(""))
            lastTime = String.valueOf(System.currentTimeMillis());

        return new SuccessMessage<List<Chats>>(null, chatService.getChatList(sessionId, lastTime)).getMessage();
    }
}
