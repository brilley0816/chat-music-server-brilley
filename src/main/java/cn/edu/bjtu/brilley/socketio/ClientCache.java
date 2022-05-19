package cn.edu.bjtu.brilley.socketio;

import com.corundumstudio.socketio.SocketIOClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Brilley
 * 参考：https://blog.csdn.net/ErHa_lodman/article/details/122625884
 * @date 2022/5/18
 */
@Component
public class ClientCache {
    private static Map<String, HashMap<UUID, SocketIOClient>> concurrentHashMap = new ConcurrentHashMap<>();

    public void saveClient(String userId, UUID sessionId, SocketIOClient socketIOClient) {
        HashMap<UUID, SocketIOClient> sessionIdClientCache = concurrentHashMap.get(userId);
        if (sessionIdClientCache == null) {
            sessionIdClientCache = new HashMap<>();
        }
        sessionIdClientCache.put(sessionId, socketIOClient);
        concurrentHashMap.put(userId, sessionIdClientCache);
    }

    public HashMap<UUID, SocketIOClient> getUserClient(String userId) {
        return concurrentHashMap.get(userId);
    }

    public void deleteSessionClientByUserId(String userId,UUID sessionId){
        concurrentHashMap.get(userId).remove(sessionId);
    }

    public void deleteUserCacheByUserId(String userId){
        concurrentHashMap.remove(userId);
    }

}
