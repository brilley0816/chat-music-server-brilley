package cn.edu.bjtu.brilley.service.impl;

import cn.edu.bjtu.brilley.dao.ChatsMapper;
import cn.edu.bjtu.brilley.dao.SessionsMapper;
import cn.edu.bjtu.brilley.domain.Chats;
import cn.edu.bjtu.brilley.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Brilley
 * @date 2022/5/22
 */
@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatsMapper chatsMapper;
    @Override
    public List<Chats> getChatList(String sessionId, String lastTime) {
        return chatsMapper.getChatList(sessionId, lastTime);
    }

    @Override
    public boolean addChat(Chats chats) {
        return chatsMapper.addChat(chats) > 0;
    }
}
