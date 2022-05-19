package cn.edu.bjtu.brilley.socketio;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * @author Brilley
 * @date 2022/5/18
 */
@Component
public class SocketIOStarter {

    @Autowired
    private SocketIOServer socketIOServer;

    /**
     * Spring IoC容器创建之后，在加载SocketIOStarter Bean之后启动
     *
     * @throws Exception
     */
    @PostConstruct
    private void autoStartup() throws Exception {
        start();
    }

    /**
     * Spring IoC容器在销毁SocketIOStarter Bean之前关闭,避免重启项目服务端口占用问题
     *
     * @throws Exception
     */
    @PreDestroy
    private void autoStop() throws Exception {
        stop();
    }

    /**
     * socketIOServer.addEventListener, ConnectionListener, DisConnectionListener等等可以单写，也可以统一写在messageEventHandler里面
     */
    private void start() {
//        socketIOServer.addEventListener("text", Object.class, (client, data, ackSender) -> {
//            client.getHandshakeData();
//            System.out.println( " 客户端：" + data);
//
//        });
        //这里不需要再重复增加 默认会去找messageEventhandler
        //socketIOServer.addListeners(messageEventHandler);
        socketIOServer.start();
        System.out.println("websocket 服务器启动！");
    }

    private void stop() {
        if (socketIOServer != null) {
            socketIOServer.stop();
            socketIOServer = null;
        }
    }
}
