package cn.edu.bjtu.brilley.service.impl;

import cn.edu.bjtu.brilley.common.WarningMessage;
import cn.edu.bjtu.brilley.dao.ConsumerMapper;
import cn.edu.bjtu.brilley.dao.SessionsMapper;
import cn.edu.bjtu.brilley.domain.Consumer;
import cn.edu.bjtu.brilley.domain.Sessions;
import cn.edu.bjtu.brilley.service.SessionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author Brilley
 * @date 2022/5/20
 */
@Service
public class SessionsServiceImpl implements SessionsService {
    @Autowired
    private SessionsMapper sessionsMapper;

    @Override
    public List<Sessions> userSessions(Integer userId, String lastTime) {

        List<Sessions> sessionsList = sessionsMapper.getSessionList(userId, lastTime);
        //判断一下是不是有系统会话
        if (isSessionExist(userId, -1)) {
            List<Sessions> tempList = sessionsMapper.getSessionById(userId, -1);
            Consumer consumer = new Consumer();
            consumer.setAvator("/img/avatorImages/systemAvator1.jpg");
            consumer.setUsername("Music-Chat系统通知");
            tempList.get(0).setConsumer(consumer);
            sessionsList.add(tempList.get(0));
        }
        return sessionsList;
    }

    @Override
    public boolean updateSessions(Integer userId1, Integer userId2, String content, Integer type) {
        //判定参数前面已经验证过了，直接进行更新，如果有错误，那不会更新成功的，也就是没有 id1 - id2的会话
        Sessions sessions = new Sessions();
        sessions.setUpdatedat(String.valueOf(System.currentTimeMillis()));
        sessions.setUserid(userId1);
        sessions.setUserid(userId2);
        sessions.setLastchat(content);

        sessions.setMsgtype(type);

        return sessionsMapper.updateSession(sessions) > 0;
    }
    @Override
    public String createSessions(Integer userId1, Integer userId2, String content, String updatedAt, Integer type) {
        String time = String.valueOf(System.currentTimeMillis());
        String sessionIdStr = time.substring(4,12);
        Sessions sessions = new Sessions();
        sessions.setSessionid(sessionIdStr);
        sessions.setUserid(userId1);
        sessions.setReceiverid(userId2);
        sessions.setLastchat(content);
        sessions.setUpdatedat(time);
        sessions.setMsgread(0);
        sessions.setMsgtype(type);

        boolean ret1 = sessionsMapper.createSession(sessions) > 0;

        sessions.setUserid(userId2);
        sessions.setReceiverid(userId1);

        boolean ret2 = sessionsMapper.createSession(sessions) > 0;

        if (ret2 && ret1) {
            return sessionIdStr;
        }
        return "";
    }

    @Override
    public boolean isSessionExist(Integer userId1, Integer userId2) {
        return sessionsMapper.isSessionExist(userId1, userId2) > 0;
    }

    @Override
    public String getSessionId(Integer userId1, Integer userId2) {
        return sessionsMapper.getSessionId(userId1, userId2);
    }
}
