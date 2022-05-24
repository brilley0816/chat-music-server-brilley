package cn.edu.bjtu.brilley.socketio;

import cn.edu.bjtu.brilley.common.ChatMessage;
import cn.edu.bjtu.brilley.common.LoginMessage;
import cn.edu.bjtu.brilley.common.ReplyMessage;
import cn.edu.bjtu.brilley.domain.ChatObject;
import cn.edu.bjtu.brilley.domain.Chats;
import cn.edu.bjtu.brilley.domain.NoticeMessage;
import cn.edu.bjtu.brilley.domain.Sessions;
import cn.edu.bjtu.brilley.service.ChatService;
import cn.edu.bjtu.brilley.service.FriendService;
import cn.edu.bjtu.brilley.service.NotificationService;
import cn.edu.bjtu.brilley.service.SessionsService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.security.auth.message.MessageInfo;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author Brilley
 * @date 2022/5/18
 */
@Component
public class MessageEventHandler {
    private static int i = 0;
    @Resource
    private ClientCache clientCache;

    @Autowired
    public SessionsService sessionsService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    FriendService friendService;

    @Autowired
    ChatService chatService;

    public static HashMap<String, SocketIOClient> socketsClient= new HashMap<>();

    //添加connect事件，当客户端发起连接时调用，本文中将clientid 与 sessionid存入数据库
    //方便后面发送消息时查找到对应的目标client,
    @OnConnect
    public void onConnect(SocketIOClient client) {

        //默认能登陆的用户已经验证过了，当然也可以再重新认证下，这里步骤最好用日志记录下

        String userId = client.getHandshakeData().getSingleUrlParam("userId");
        System.out.println("client id: "+ userId + " connected, it's session: " + client.getSessionId());
        if(socketsClient.containsKey(userId)) {
            socketsClient.remove(userId);
        }
        socketsClient.put(userId,client);

        //初始登陆就进行websocket连接，在后台发送消息给客户端；
        //前端可以在state值保存一个数，在点击聊天界面时，如果状态为真则直接进行对话，否则重新连接！当然，默认进入界面是连接成功的

        client.sendEvent("brilley","你好，我是服务器，你已经连接成功了！");
        System.out.println("连接成功，并发送brilley类型数据给user "+ userId);

        LoginMessage loginMessage = new LoginMessage("login", "success", "登录通知", String.valueOf(System.currentTimeMillis()));
        client.sendEvent("message", loginMessage.getMessage());

        //保存通知记录
        notificationService.addNotification(new Integer(userId), -1, String.valueOf(System.currentTimeMillis()));
        //更新session
        sessionsService.updateSessions(new Integer(userId), -1, "登录通知", 2);

    }

    //添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        String userId = client.getHandshakeData().getSingleUrlParam("userId");
        if (socketsClient.containsKey(userId))
            socketsClient.remove(userId);
        System.out.println("userId: "+userId+" disconnect successfully");
    }

    //消息接收入口，当接收到消息后，查找发送目标客户端，并且向该客户端发送消息，且给自己发送消息
    @OnEvent(value = "chat")
    public void onEvent(SocketIOClient client, Object data, AckRequest ackRequest) {
        //直接打印的类型是 {content=好, receiverId=48, uuid=22659911-a7ec-76dd-f737-4b8a28fd3c07, type=0, sessionId=38188174}
        System.out.println("收到消息 chat: "+ data.toString());
        //先转成json字符串类型 {"content":"好","receiverId":48,"uuid":"22659911-a7ec-76dd-f737-4b8a28fd3c07","type":0,"sessionId":"38188174"}
        String jsonstr = JSONObject.toJSONString(data);
        //再转成JsonObject，对每个字符可以操作
        JSONObject jsonObject = JSON.parseObject(jsonstr);

        String userIdStr = client.getHandshakeData().getSingleUrlParam("userId");
        Integer senderId = new Integer(userIdStr);
        System.out.println(senderId);
        Integer receiverId = new Integer(jsonObject.get("receiverId").toString());
        String sessionId = jsonObject.get("sessionId").toString();
        String content = jsonObject.get("content").toString();
        Integer type = new Integer(jsonObject.get("type").toString());
        String uuid = jsonObject.get("uuid").toString();

        System.out.println("content: "+ content);
        System.out.println("sessionId: " + sessionId);
        //判断是不是好友关系
        boolean isFriend = friendService.getIsFriend(senderId, receiverId);
        System.out.println(isFriend);

        String keepContent = content.length() < 100 ? content : content.substring(0,100);
        sessionsService.updateSessions(senderId, receiverId, keepContent,type);

        //把聊天记录插入数据库
        Chats chats = new Chats();
        chats.setUpdatedat(String.valueOf(System.currentTimeMillis()));
        chats.setSessionid(sessionId);
        chats.setType(type);
        chats.setSenderid(senderId);
        chats.setReceiverid(receiverId);
        chats.setContent(content);
        if (!chatService.addChat(chats)) {
            System.out.println("插入聊天记录失败");
        }

        //返回给发送方
        ReplyMessage replyMessage = new ReplyMessage("chat", "success", String.valueOf(System.currentTimeMillis()), content, uuid, sessionId, receiverId, type);
        client.sendEvent("message", replyMessage.getMessage());
        System.out.println("发送给发送方");

        //发送给接收方，判断用户是否在线，也就是有没有对应的IOclient
        if (socketsClient.containsKey(receiverId.toString())) {
            SocketIOClient receiverClient = socketsClient.get(receiverId.toString());
            ChatMessage chatMessage = new ChatMessage(content, String.valueOf(System.currentTimeMillis()), sessionId, senderId, type);
            receiverClient.sendEvent("chat", chatMessage.getMessage());
            System.out.println("发送给接收方");
        }

    }


    //好友添加通知
    @OnEvent(value = "request")
    public void friendRequest(SocketIOClient client, Object data, AckRequest ackRequest) {
        //判断发送方是不是存在,不是则提醒用户，是则发送给接收方
        System.out.println("收到request类消息");
    }

    @OnEvent(value = "text")
    public void helloEvent(SocketIOClient client, Object data, AckRequest ackRequest) {
        System.out.println("收到消息text: " + data);
        String jsonStr = "{\"msgType\":\"login\",\"status\":\"success\",\"content\":\"登陆通知\",\"time\":\"123334434\"}";
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        System.out.println("发送message类型消息：" + jsonObject.toJSONString());
        client.sendEvent("message",jsonObject);

        int time1 = (int) System.currentTimeMillis();
        NoticeMessage meg2 = new NoticeMessage("login", "success", "登陆通知2", time1);
        System.out.println("发送第二条消息：" + meg2.toString());
        client.sendEvent("message", JSONObject.parseObject(meg2.toString()));
    }

}
