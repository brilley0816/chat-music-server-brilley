package cn.edu.bjtu.brilley.service;

import cn.edu.bjtu.brilley.domain.Friends;

import java.util.List;

/**
 * @author Brilley
 * @date 2022/5/22
 */
public interface FriendService {

    boolean getIsFriend(Integer userId, Integer friendId);

    boolean addFriend(Integer userId, Integer friendId);

    boolean deleteFriend(Integer userId, Integer friendId);

    List<Friends> getFriendList(Integer userId);

    List<Friends> getFriendInfoById(Integer userId, Integer friendId);
}
