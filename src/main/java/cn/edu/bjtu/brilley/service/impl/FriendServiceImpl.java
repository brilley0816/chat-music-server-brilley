package cn.edu.bjtu.brilley.service.impl;

import cn.edu.bjtu.brilley.dao.FriendRequestsMapper;
import cn.edu.bjtu.brilley.dao.FriendsMapper;
import cn.edu.bjtu.brilley.domain.Friends;
import cn.edu.bjtu.brilley.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Brilley
 * @date 2022/5/22
 */
@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendsMapper friendsMapper;

    @Override
    public boolean getIsFriend(Integer userId, Integer friendId) {
        return friendsMapper.getIsFriend(userId, friendId) > 0;
    }

    @Override
    public boolean addFriend(Integer userId, Integer friendId) {
        Friends friends = new Friends();
        friends.setCreatedat(String.valueOf(System.currentTimeMillis()));
        friends.setFriendid(friendId);
        friends.setUserid(userId);
        friends.setUpdatedat(String.valueOf(System.currentTimeMillis()));
        boolean ret1 = friendsMapper.addFriend(friends) > 0;

        friends.setCreatedat(String.valueOf(System.currentTimeMillis()));
        friends.setFriendid(userId);
        friends.setUserid(friendId);
        friends.setUpdatedat(String.valueOf(System.currentTimeMillis()));
        boolean ret2 = friendsMapper.addFriend(friends) > 0;

        return ret1 && ret2;
    }

    @Override
    public boolean deleteFriend(Integer userId, Integer friendId) {
        return friendsMapper.deleteFriend(userId, friendId) > 0;
    }

    @Override
    public List<Friends> getFriendList(Integer userId) {
        return friendsMapper.getFriendList(userId);
    }

    @Override
    public List<Friends> getFriendInfoById(Integer userId, Integer friendId) {
        return friendsMapper.getFriendInfoById(userId, friendId);
    }
}
