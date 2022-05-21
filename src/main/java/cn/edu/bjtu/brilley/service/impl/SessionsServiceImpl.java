package cn.edu.bjtu.brilley.service.impl;

import cn.edu.bjtu.brilley.dao.ConsumerMapper;
import cn.edu.bjtu.brilley.dao.SessionsMapper;
import cn.edu.bjtu.brilley.domain.Sessions;
import cn.edu.bjtu.brilley.service.SessionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
        return sessionsMapper.getSessionList(userId, lastTime);
    }

    @Override
    public int updateSessions(Integer userId1, Integer userId2, String content, Integer type) {
        //系统通知
        if(type == 0) {
            //为了区别是不是首次会话，要先判断会话是不是已经存在过
        }
        else if (type == 1) {

        }
        else if (type == 2) {

        }
        return -1;
    }
    @Override
    public int createSessions(Integer userId1, Integer userId2, String content, String updatedAt, Integer type) {
        String time = String.valueOf(System.currentTimeMillis());
        Sessions sessions = new Sessions();
        sessions.setSessionid(time.substring(4,12));
        sessions.setUserid(userId1);
        sessions.setReceiverid(userId2);
        sessions.setLastchat(content);
        sessions.setUpdatedat(time);
        sessions.setMsgread(0);
        sessions.setMsgtype(type);
        if (sessionsMapper.createSession(sessions) != 0)
            return 2;
        return -1;
    }
}
