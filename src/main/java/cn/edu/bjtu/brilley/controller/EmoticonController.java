package cn.edu.bjtu.brilley.controller;

import cn.edu.bjtu.brilley.common.SuccessMessage;
import cn.edu.bjtu.brilley.common.WarningMessage;
import cn.edu.bjtu.brilley.domain.Emoticons;
import cn.edu.bjtu.brilley.domain.Notifications;
import cn.edu.bjtu.brilley.service.EmoticonService;
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
public class EmoticonController {
    @Autowired
    private EmoticonService emoticonService;

    /**
     * 只是添加自定义表情
     * @param req
     * @param
     * @return
     */
    @RequestMapping(value = "/emoticons/get_emoticon_list", method = RequestMethod.GET)
    public Object getEmoticonList(HttpServletRequest req) {
        String userIdStr = req.getParameter("userId").trim();
        System.out.println("Request emoticon param userId is: " + userIdStr);
        Pattern pattern = Pattern.compile("^\\d{1,9}$");
        if (!pattern.matcher(userIdStr).matches())
            return new WarningMessage("请求参数userId不合法，获取Emoticon失败").getMessage();

        Integer userId = new Integer(userIdStr);
        return new SuccessMessage<List<Emoticons>>(null, emoticonService.getEmoticonList(userId)).getMessage();
    }

    @RequestMapping(value = "/emoticons/add_emoticon", method = RequestMethod.GET)
    public Object addEmoticon(HttpServletRequest req) {
        String srcStr = req.getParameter("src").trim();
        System.out.println("add emoticon param src is: " + srcStr);
        if(srcStr.equals(""))
            return new WarningMessage("请求参数src不合法，获取Emoticon失败").getMessage();

        String userIdStr = req.getParameter("userId").trim();
        System.out.println("Request emoticon param userId is: " + userIdStr);
        Pattern pattern = Pattern.compile("^\\d{1,9}$");
        if (!pattern.matcher(userIdStr).matches())
            return new WarningMessage("请求参数userId不合法，获取聊天记录失败").getMessage();
        Integer userId = new Integer(userIdStr);

        return new SuccessMessage<Integer>(null, emoticonService.addEmoticon(userId, srcStr)).getMessage();
    }


}
