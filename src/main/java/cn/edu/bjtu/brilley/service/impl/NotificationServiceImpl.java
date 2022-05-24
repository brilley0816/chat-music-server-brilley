package cn.edu.bjtu.brilley.service.impl;

import cn.edu.bjtu.brilley.dao.NotificationsMapper;
import cn.edu.bjtu.brilley.domain.Notifications;
import cn.edu.bjtu.brilley.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Brilley
 * @date 2022/5/23
 */
@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private NotificationsMapper notificationsMapper;

    @Override
    public List<Notifications> getNotificationList(Integer userId, String lastTime) {
        return notificationsMapper.getNotificationList(userId, lastTime);
    }

    @Override
    public boolean addNotification(Integer userId, Integer type, String createdAt) {
        Notifications notifications = new Notifications();
        notifications.setCreatedat(createdAt);
        notifications.setUserid(userId);
        notifications.setType(type);
        return notificationsMapper.addNotification(notifications) > 0;
    }
}
