package cn.edu.bjtu.brilley.controller;

import cn.edu.bjtu.brilley.common.SuccessMessage;
import cn.edu.bjtu.brilley.common.WarningMessage;
import cn.edu.bjtu.brilley.domain.Chats;
import cn.edu.bjtu.brilley.domain.Notifications;
import cn.edu.bjtu.brilley.service.ChatService;
import cn.edu.bjtu.brilley.service.NotificationService;
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
 * @date 2022/5/23
 */
@RestController
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value = "/notifications/get_notification_list", method = RequestMethod.GET)
    public Object getNotificationList(HttpServletRequest req) {
        String userIdStr = req.getParameter("userId").trim();
        Pattern pattern = Pattern.compile("^\\d{1,9}$");
        if (!pattern.matcher(userIdStr).matches())
            return new WarningMessage("请求参数userId不合法，获取聊天记录失败").getMessage();
        Integer userId = new Integer(userIdStr);
        String lastTime = req.getParameter("lastTime").trim();
        System.out.println("param lastTime is: " + lastTime + " ---  param userId is: " + userId);
        if (lastTime.equals(""))
            lastTime = String.valueOf(System.currentTimeMillis());

        return new SuccessMessage<List<Notifications>>(null, notificationService.getNotificationList(userId, lastTime)).getMessage();
    }

}
