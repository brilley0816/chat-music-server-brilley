package cn.edu.bjtu.brilley;

import cn.edu.bjtu.brilley.dao.FriendRequestsMapper;
import cn.edu.bjtu.brilley.dao.FriendsMapper;
import cn.edu.bjtu.brilley.dao.SessionsMapper;
import cn.edu.bjtu.brilley.domain.Consumer;
import cn.edu.bjtu.brilley.domain.FriendRequests;
import cn.edu.bjtu.brilley.domain.Friends;
import cn.edu.bjtu.brilley.domain.Sessions;
import cn.edu.bjtu.brilley.service.FriendRequestService;
import cn.edu.bjtu.brilley.service.SessionsService;
import cn.edu.bjtu.brilley.service.impl.ConsumerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

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
    @Autowired
    private FriendsMapper friendsMapper;

    @Autowired
    private FriendRequestsMapper friendRequestsMapper;

    @Autowired
    FriendRequestService friendRequestService;

    @Autowired
    SessionsMapper sessionsMapper;

    @Autowired
    SessionsService sessionsService;

    @Test
    public void testGetFriendList() {
//        List<Friends> friendsList = friendsMapper.getFriendInfoById(1,2);
//        for (Friends friends : friendsList) {
//            System.out.println(friends.getUserid());
//            System.out.println(friends.getFriendid());
//            System.out.println(friends.getConsumer().getUsername());
//        }

//        FriendRequests friendRequest = new FriendRequests();
//        friendRequest.setHandle(1);
//        friendRequest.setMsgread(1);
//        friendRequest.setContent("Iloveyanyan");
//        friendRequest.setCreatedat(String.valueOf(System.currentTimeMillis()));
//        friendRequest.setUserid(1);
//        friendRequest.setFriendid(3);
//
//        //按friendId, userId来update
//        boolean rets = friendRequestService.updateFriendRequest(friendRequest);
//        System.out.println(rets);

        System.out.println(sessionsMapper.getSessionId(1,2));
        List<Sessions> sessionsList = sessionsMapper.getSessionList(1,String.valueOf(System.currentTimeMillis()));
        for (Sessions sessions : sessionsList) {
            System.out.println(sessions.getSessionid());
            System.out.println(sessions.getUserid());
            System.out.println(sessions.getReceiverid());
            System.out.println(sessions.getConsumer().getUsername());
            System.out.println(sessions.getConsumer().getId());
            System.out.println(sessions.getConsumer().getAvator());
            System.out.println("************************");
        }
        List<Sessions> tempList = sessionsMapper.getSessionById(1, -1);
        for (Sessions sessions : tempList) {
            System.out.println(sessions.getSessionid());
            System.out.println(sessions.getUserid());
            System.out.println(sessions.getReceiverid());
            //不赋值会报空指针异常
//            System.out.println(sessions.getConsumer().getUsername());
//            System.out.println(sessions.getConsumer().getId());
//            System.out.println(sessions.getConsumer().getAvator());
            System.out.println("************************");
        }
        Consumer consumer = new Consumer();
        consumer.setAvator("heihie");
        consumer.setUsername("系统通知");
        tempList.get(0).setConsumer(consumer);
        sessionsList.add(tempList.get(0));
        for (Sessions sessions : sessionsList) {
            System.out.println(sessions.getSessionid());
            System.out.println(sessions.getUserid());
            System.out.println(sessions.getReceiverid());
            System.out.println(sessions.getConsumer().getUsername());
            System.out.println(sessions.getConsumer().getId());
            System.out.println(sessions.getConsumer().getAvator());
            System.out.println("************************");
        }
    }

    @Test
    public void testSessionService() {
        List<Sessions> sessionsList = sessionsService.userSessions(1,String.valueOf(System.currentTimeMillis()));
        for (Sessions sessions : sessionsList) {
            System.out.println(sessions.getSessionid());
            System.out.println(sessions.getUserid());
            System.out.println(sessions.getReceiverid());
            System.out.println(sessions.getConsumer().getUsername());
            System.out.println(sessions.getConsumer().getId());
            System.out.println(sessions.getConsumer().getAvator());
            System.out.println("************************");
        }
    }

}
