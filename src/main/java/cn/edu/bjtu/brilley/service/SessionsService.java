package cn.edu.bjtu.brilley.service;

import cn.edu.bjtu.brilley.domain.Consumer;
import cn.edu.bjtu.brilley.domain.Sessions;

import java.util.List;

/**
 * @author Brilley
 * @date 2022/5/20
 */
public interface SessionsService {

    List<Sessions> userSessions(Integer userId, String lastTime);

    /**
     * 创建会话，返回会话uuid
     * @return
     */
    String createSessions(Integer userId1, Integer userId2, String content, String updatedAt, Integer type);

    boolean updateSessions(Integer userId1, Integer userId2, String content, Integer type);

    boolean isSessionExist(Integer userId1, Integer userId2);

    String getSessionId(Integer userId1, Integer userId2);
}
