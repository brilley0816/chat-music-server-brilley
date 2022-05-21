package cn.edu.bjtu.brilley.service;

import cn.edu.bjtu.brilley.domain.Chats;
import cn.edu.bjtu.brilley.domain.Sessions;

import java.util.List;

/**
 * @author Brilley
 * @date 2022/5/22
 */
public interface ChatService {

    List<Chats> getChatList(String sessionId, String lastTime);
}
