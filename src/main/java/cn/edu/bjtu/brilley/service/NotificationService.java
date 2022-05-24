package cn.edu.bjtu.brilley.service;

import cn.edu.bjtu.brilley.domain.Notifications;

import java.util.List;

/**
 * @author Brilley
 * @date 2022/5/23
 */
public interface NotificationService {
    List<Notifications> getNotificationList(Integer userId, String lastTime);

    boolean addNotification(Integer userId, Integer type, String createdAt);
}
