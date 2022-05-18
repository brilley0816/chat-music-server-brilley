package cn.edu.bjtu.brilley;

import cn.edu.bjtu.brilley.domain.Consumer;
import cn.edu.bjtu.brilley.service.impl.ConsumerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class MusicWebchatBrilleyApplicationTests {

    @Autowired
    private ConsumerServiceImpl consumerService;

//    @Test
//    void contextLoads() {
//        Consumer consumer = new Consumer();
//        consumer.setUsername("setlove");
//        consumer.setPassword("123");
//        consumer.setSex(new Byte("0"));
//        consumer.setPhoneNum("15666412237");
//        consumer.setEmail("1239679@qq.com");
//        consumer.setBirth(new Date());
//        consumer.setIntroduction("");
//        consumer.setLocation("");
//        consumer.setAvator("/img/avatorImages/user.jpg");
//        consumer.setCreateTime(new Date());
//        consumer.setUpdateTime(new Date());
//        consumerService.addUser(consumer);
//
//    }

}
