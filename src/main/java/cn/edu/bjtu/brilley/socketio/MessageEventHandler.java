package cn.edu.bjtu.brilley.socketio;

import cn.edu.bjtu.brilley.domain.ChatObject;
import com.alibaba.fastjson.JSONObject;
import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");

    //添加connect事件，当客户端发起连接时调用，本文中将clientid 与 sessionid存入数据库
    //方便后面发送消息时查找到对应的目标client,
    @OnConnect
    public void onConnect(SocketIOClient client) {

        String userId = client.getHandshakeData().getSingleUrlParam("userId");
        System.out.println("client id: "+userId + " connected, it's session: " + client.getSessionId());
        System.out.println(i);
        i++;
        long t1 = System.currentTimeMillis();
        System.out.println(t1);

        UUID sessioId = client.getSessionId();
        clientCache.saveClient(userId, sessioId, client);
        String jsonStr = "{\"msgType\":\"login\",\"status\":\"success\",\"content\":\"登陆通知\",\"time\":\"123334434\"}";
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        ChatObject chatObject = new ChatObject("brilley","hello");
        System.out.println(JSONObject.toJSON(chatObject));
        client.sendEvent("message",JSONObject.toJSON(chatObject));
        System.out.println("发数据");
//        HandshakeData hd = client.getHandshakeData();
//        String auth_token = hd.getSingleUrlParam("auth_token");
//        UserEntity userEntity = userSerivice.findUserByToken(auth_token);
//        String userId = userEntity.getId();
//        String userName = userEntity.getUsername();
//        client.set("userId", userId);
//        client.set("userName", userName);
//        SessionUtil.userId_socket_Map.put(userId, client);
//
//        //上线关联所在的群组
//        List<GroupEntity> entityList = groupSerivice.findMyGroupsByUserId(userId);
//
//        for (GroupEntity entity : entityList) {
//            logger.info(userName + "自动关联了群 " + entity.getGroupname() + "   " + sdf.format(new Date()));
//            client.joinRoom(entity.getId());
//        }
//
//        logger.info(userName + "---》上 === 线了  " + client.getSessionId() + "   " + sdf.format(new Date()));
    }

    //添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        String userId = client.getHandshakeData().getSingleUrlParam("userId");
        UUID sessionId = client.getSessionId();
        clientCache.deleteSessionClientByUserId(userId,sessionId);
        System.out.println("userId: "+userId+" disconnect successfully, session id: "+sessionId);
    }

    //消息接收入口，当接收到消息后，查找发送目标客户端，并且向该客户端发送消息，且给自己发送消息
    @OnEvent(value = "chat")
    public void onEvent(SocketIOClient client, Object data, AckRequest ackRequest) {
        System.out.println("chat: "+ data);
        String jsonStr = "{\"msgType\":\"login\",\"status\":\"success\",\"content\":\"登陆通知\",\"time\":\"123334434\"}";
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        client.sendEvent("message", jsonObject);
//        boolean isChat = msg.getChat_type().toString().equals("chat");
//
//        //ack 返回数据 服务器收到发送的数据
//        if (ackRequest.isAckRequested()) {
//
//            if (msg.getFrom_user().equals(msg.getTo_user())) {
//                ackRequest.sendAckData("请不要给自己发消息");
//                return;
//            }
//
//            //将数据保存到服务器
//            chatSerivice.saveMessageData(msg);
//
//            String toName = "";
//            if (isChat) {
//                toName = msg.getTo_user();
//            } else {
//                toName = "群： " + msg.getTo_user();
//            }
//            logger.info("给 " + toName + " 发送的数据 服务器已经收到， 日期： " + sdf.format(new Date()));
//            //发送ack回调数据到客户端
//            ackRequest.sendAckData("");
//        }
//
//
//        String to_user_id = msg.getTo_user_id(); //如果是 群聊，则对应群的id
//        String to_user_name = msg.getTo_user();
//
//        if (isChat) { //单聊
//            // 如果对方在线 则找到对应的client 给其发送消息
//            if (SessionUtil.userId_socket_Map.containsKey(to_user_id)) {
//                SessionUtil.userId_socket_Map.get(to_user_id).sendEvent("chat", new AckCallback<String>(String.class) {
//                    //对方客户端接收到消息 返回给服务器端
//                    @Override
//                    public void onSuccess(String result) {
//                        logger.info(to_user_name + "已收到消息 ， ack 回复 ： " + result + "    日期： " + sdf.format(new Date()));
//                    }
//
//                    //发送消息超时
//                    @Override
//                    public void onTimeout() {
//                        System.out.println(to_user_name + "---------》发送消息超时 " + sdf.format(new Date()));
//                    }
//                }, msg);
//            } else { //如果 下线 转apns 消息推送
//                logger.info(to_user_name + "---------》需要转 apns 消息推送 " + sdf.format(new Date()));
//            }
//        } else {  //群聊
//
//            logger.info("========================发送群消息==================================");
//
////            server.getBroadcastOperations().sendEvent("groupChat",msg); //系统广播
//            // 房间（群内）广播
//            server.getRoomOperations(to_user_id).sendEvent("chat", msg, new BroadcastAckCallback<String>(String.class) {
//                @Override
//                protected void onClientTimeout(SocketIOClient client) {
//                    logger.info("群消息: " + client.get("userName") + " 发送超时了");
//                }
//
//                @Override
//                protected void onClientSuccess(SocketIOClient client, String result) {
//                    logger.info("群消息: " + client.get("userName") + " 已接收到" + DateUtils.getDataTimeYMDHMSS());
//                }
//            });
//        }

    }


    @OnEvent(value = "text")
    public void helloEvent(SocketIOClient client, Object data, AckRequest ackRequest) {
        System.out.println(data);
        System.out.println(i);
        i++;
        System.out.println("为什么会发两次消息");
    }

}
