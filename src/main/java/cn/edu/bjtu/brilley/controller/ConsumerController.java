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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
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
        //前端设计只保留这三个部分的注册信息；
        String sex = "2"; //这里不可以为空
        String email = "";
        String birth = "2000-10-10";
        String introduction = "";
        String location = "";
        String avator = "/img/avatorImages/user.jpg"; //设置默认头像

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
            int userId = consumerService.getIdbyName(username);
            System.out.println("get userId"+userId);
            session.setAttribute("userId", userId);
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
        //return consumerService.userOfId(Integer.parseInt(id));
        //return new SuccessMessage<List<Consumer>>("获取成功", consumerService.userOfId(Integer.parseInt(id)));//这里就是因为没有加上getMessage，返回的是SuccessMessage Object,而不是json对象
        return new SuccessMessage<List<Consumer>>("获取成功", consumerService.userOfId(Integer.parseInt(id))).getMessage();
    }
    /**
     * 删除用户
     */
    @RequestMapping(value = "/user/delete", method = RequestMethod.GET)
    public Object deleteUser(HttpServletRequest req) {
        String id = req.getParameter("id");

        boolean res = consumerService.deleteUser(Integer.parseInt(id));
        if (res) {
            return new SuccessMessage<Null>("删除成功").getMessage();
        } else {
            return new ErrorMessage("删除失败").getMessage();
        }
    }

    /**
     * 更新用户信息
     */
    @ResponseBody
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public Object updateUserMsg(HttpServletRequest req) {
        String id = req.getParameter("id").trim();
        String username = req.getParameter("username").trim();
        String sex = req.getParameter("sex").trim();
        String phone_num = req.getParameter("phone_num").trim();
        String email = req.getParameter("email").trim();
        String birth = req.getParameter("birth").trim();
        String introduction = req.getParameter("introduction").trim();
        String location = req.getParameter("location").trim();

        Consumer consumer = new Consumer();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myBirth = new Date();
        try {
            myBirth = dateFormat.parse(birth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        consumer.setId(Integer.parseInt(id));
        consumer.setUsername(username);
        consumer.setSex(new Byte(sex));
        consumer.setPhoneNum(phone_num);
        consumer.setEmail(email);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        consumer.setUpdateTime(new Date());
        consumer.setBirth(myBirth);

        boolean res = consumerService.updateUserMsg(consumer);
        if (res) {
            return new SuccessMessage<Null>("修改成功").getMessage();
        } else {
            return new ErrorMessage("修改失败").getMessage();
        }
    }

    /**
     * 更新用户密码
     */
    @ResponseBody
    @RequestMapping(value = "/user/updatePassword", method = RequestMethod.POST)
    public Object updatePassword(HttpServletRequest req) {
        String id = req.getParameter("id").trim();
        String username = req.getParameter("username").trim();
        String old_password = req.getParameter("old_password").trim();
        String password = req.getParameter("password").trim();

        boolean res = consumerService.verifyPasswd(username, old_password);
        if (!res) {
            return new ErrorMessage("密码输入错误").getMessage();
        }

        Consumer consumer = new Consumer();
        consumer.setId(Integer.parseInt(id));
        consumer.setPassword(password);

        boolean result = consumerService.updatePassword(consumer);
        if (result) {
            return new SuccessMessage<Null>("密码修改成功").getMessage();
        } else {
            return new ErrorMessage("密码修改失败").getMessage();
        }
    }

    /**
     * 更新用户头像
     */
    @ResponseBody
    @RequestMapping(value = "/user/avatar/update", method = RequestMethod.POST)
    public Object updateUserPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        String filePath = Constants.PROJECT_PATH + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "avatorImages";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String imgPath = "/img/avatorImages/" + fileName;
        try {
            avatorFile.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(imgPath);
            boolean res = consumerService.updateUserAvator(consumer);
            if (res) {
                return new SuccessMessage<String>("上传成功", imgPath).getMessage();
            } else {
                return new ErrorMessage("上传失败").getMessage();
            }
        } catch (IOException e) {
            return new FatalMessage("上传失败" + e.getMessage()).getMessage();
        }
    }


}
