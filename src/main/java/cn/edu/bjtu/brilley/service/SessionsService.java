package cn.edu.bjtu.brilley.service;

import cn.edu.bjtu.brilley.domain.Consumer;
import cn.edu.bjtu.brilley.domain.Sessions;

import java.util.List;

/**
 * @author Brilley
 * @date 2022/5/20
 */
public interface SessionsService {

    List<Sessions> userSessions(String userId, String lastTime);

    /**
     * 创建会话，返回会话uuid
     * @return
     */
    int createSessions(Integer userId1, Integer userId2, String content, int type);

    int updateSessions(Integer userId1, Integer userId2, String content, int type);
}
