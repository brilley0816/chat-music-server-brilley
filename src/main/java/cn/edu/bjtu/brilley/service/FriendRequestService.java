package cn.edu.bjtu.brilley.service;

import cn.edu.bjtu.brilley.domain.FriendRequests;

import java.util.List;

/**
 * @author Brilley
 * @date 2022/5/22
 */
public interface FriendRequestService {

    List<FriendRequests> getFriendRequestList(Integer userId, String lastTime);

    List<FriendRequests> getFriendRequestById(Integer requestId);

    boolean addFriendRequest(Integer userId, Integer frinedId, String content);

    boolean updateFriendRequest(FriendRequests friendRequests);

    boolean isExistFriendRequest(Integer userId, Integer friendId);

}
