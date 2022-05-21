package cn.edu.bjtu.brilley.socketio;

import cn.edu.bjtu.brilley.domain.ChatObject;
import cn.edu.bjtu.brilley.domain.NoticeMessage;
import cn.edu.bjtu.brilley.service.SessionsService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.security.auth.message.MessageInfo;
import java.text.SimpleDateFormat;
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

    private SessionsService sessionsService;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");

    //添加connect事件，当客户端发起连接时调用，本文中将clientid 与 sessionid存入数据库
    //方便后面发送消息时查找到对应的目标client,
    @OnConnect
    public void onConnect(SocketIOClient client) {

        //默认能登陆的用户已经验证过了，当然也可以再重新认证下，这里步骤最好用日志记录下

        String userId = client.getHandshakeData().getSingleUrlParam("userId");
        System.out.println("client id: "+ userId + " connected, it's session: " + client.getSessionId());
        UUID sessionId = client.getSessionId();
        clientCache.saveClient(userId, sessionId, client);

        //初始登陆就进行websocket连接，在后台发送消息给客户端；
        //前端可以在state值保存一个数，在点击聊天界面时，如果状态为真则直接进行对话，否则重新连接！当然，默认进入界面是连接成功的
        client.sendEvent("brilley","你好，我是服务器，你已经连接成功了！");
        System.out.println("连接成功，并发送brilley类型数据给user "+ userId);

        //登录通知要发嘛？可以发，在用户点击聊天界面的时候进行,还是登陆的时候就发吧，给用户上线的一个时间记录，也方便，不然在点击界面的时候发，还得再制定规则
//        String jsonStr = "{\"msgType\":\"login\",\"status\":\"success\",\"content\":\"登陆通知\",\"time\":\"123334434\"}";
//        SONObject jsonObject = JSONObject.parseObject(jsonStr);
        client.sendEvent("");

        //交给SessionService去更新Session
        //sessionsService.updateSessions(Integer.valueOf(userId), -1, "登录通知",1);

        //记录通知

    }

    //添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        String userId = client.getHandshakeData().getSingleUrlParam("userId");
        UUID sessionId = client.getSessionId();
        clientCache.deleteSessionClientByUserId(userId,sessionId);
        System.out.println("userId: "+userId+" disconnect successfully");
    }

    //消息接收入口，当接收到消息后，查找发送目标客户端，并且向该客户端发送消息，且给自己发送消息
    @OnEvent(value = "chat")
    public void onEvent(SocketIOClient client, Object data, AckRequest ackRequest) {
        System.out.println("收到消息 chat: "+ data);

        //判断是不是好友关系
        int isFriend = 1;
        if (isFriend == 0) {
            client.sendEvent("message","对方不是您的好友了");
            return;
        }

        //判断发送的内容类型
        String content= "";
        if (data == null) {

        }
        else {

        }

        //更新会话列表

        //将聊天记录保存到数据库

        //返回给发送方

        //判断接收方是不是存在，存在则发送数据



    }


    //好友添加通知
    @OnEvent(value = "request")
    public void friendRequest(SocketIOClient client, Object data, AckRequest ackRequest) {
        //判断发送方是不是存在,不是则提醒用户，是则发送给接收方

        //

        System.out.println("发送message类型消息：");
        String jsonStr = "{\"msgType\":\"login\",\"status\":\"success\",\"content\":\"登陆通知\",\"time\":\"123334434\"}";
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        client.sendEvent("message",jsonObject);
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
