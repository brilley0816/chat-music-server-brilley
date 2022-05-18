package cn.edu.bjtu.brilley.controller;

import cn.edu.bjtu.brilley.common.ErrorMessage;
import cn.edu.bjtu.brilley.common.FatalMessage;
import cn.edu.bjtu.brilley.common.SuccessMessage;
import cn.edu.bjtu.brilley.common.WarningMessage;
import cn.edu.bjtu.brilley.constant.Constants;
import cn.edu.bjtu.brilley.domain.Consumer;
import cn.edu.bjtu.brilley.service.impl.ConsumerServiceImpl;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Brilley
 * @date 2022/5/13
 */

@RestController
@Controller
public class ConsumerController {

    private static final Logger logger = LogManager.getLogger(ConsumerController.class);
    @Autowired
    private ConsumerServiceImpl consumerService;

    @Configuration
    public static class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/avatorImages/**")
                    .addResourceLocations(Constants.AVATOR_IMAGES_PATH);
        }
    }
    /**
     * 用户注册
     * @param req
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public Object addUser(HttpServletRequest req) {
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        String phone_num = req.getParameter("phone_num").trim();
        //前端设计进只保留这三个部分的注册信息；
        String sex = "0"; //这里不可以为空
        String email = "example";
        String birth = "1996-04-05";
        String introduction = "";
        String location = "";
        String avator = "/img/avatorImages/user.jpg";

        if(consumerService.existUser(username)) {
            return new WarningMessage("用户名已注册").getMessage();
        }

        if(consumerService.existPhoneNum(phone_num)) {
            return new WarningMessage("手机号已注册").getMessage();
        }

        Consumer consumer = new Consumer();
        //默认出生日期 1996-04-05
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDay = new Date();
        try {
            birthDay = dateFormat.parse(birth);
        } catch ( Exception e) {
            e.printStackTrace();
        }
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex));
        if ("".equals(phone_num)) {
            consumer.setPhoneNum(null);
        } else {
            consumer.setPhoneNum(phone_num);
        }
        if ("".equals(email)) {
            consumer.setEmail(null);
        } else {
            consumer.setEmail(email);
        }
        consumer.setBirth(birthDay);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        consumer.setAvator(avator);
        consumer.setCreateTime(new Date());
        consumer.setUpdateTime(new Date());

        try {
            boolean res = consumerService.addUser(consumer);
            if (res) {
                return new SuccessMessage<Null>("注册成功").getMessage();
            } else {
                return new ErrorMessage("注册失败").getMessage();
            }
        } catch (DuplicateKeyException e) {
            return new FatalMessage(e.getMessage()).getMessage();
        }
    }

    @ResponseBody
    @RequestMapping(value = "user/login/status", method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest req, HttpSession session) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        boolean res = consumerService.verifyPasswd(username, password);
        if (res) {
            session.setAttribute("username", username);
            //这里为什么是返回一个Consumer对象的List，不应该返回一个对象就行了嘛？
            //解答，sql查询的结果是列表，一条信息也是列表
            return new SuccessMessage<List<Consumer>>("登录成功", consumerService.loginStatus(username)).getMessage();
        } else {
            return new ErrorMessage("用户名或者密码错误").getMessage();
        }
    }

    /**
     *查询所有用户信息
     * @return
     */
    @RequestMapping(value = "/allUser", method = RequestMethod.GET)
    public Object allUser() {
        return consumerService.allUser();
    }

    @RequestMapping(value = "/user/detail", method = RequestMethod.GET)
    public Object userOfId(HttpServletRequest req) {
        String id = req.getParameter("id");
        System.out.println(req);
        return new SuccessMessage<List<Consumer>>(null, consumerService.userOfId(Integer.parseInt(id)));
    }


}
