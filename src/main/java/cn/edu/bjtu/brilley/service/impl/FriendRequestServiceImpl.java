package cn.edu.bjtu.brilley.service.impl;

import cn.edu.bjtu.brilley.dao.FriendRequestsMapper;
import cn.edu.bjtu.brilley.domain.FriendRequests;
import cn.edu.bjtu.brilley.service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Brilley
 * @date 2022/5/22
 */
@Service
public class FriendRequestServiceImpl implements FriendRequestService {

    @Autowired
    private FriendRequestsMapper friendRequestsMapper;

    @Override
    public List<FriendRequests> getFriendRequestList(Integer userId, String lastTime) {
        return friendRequestsMapper.getFriendRequestList(userId, lastTime);
    }

    @Override
    public List<FriendRequests> getFriendRequestById(Integer requestId) {
        return friendRequestsMapper.getFriendRequestById(requestId);
    }

    @Override
    public boolean addFriendRequest(Integer userId, Integer friendId, String content) {
        FriendRequests friendRequests = new FriendRequests();
        friendRequests.setContent(content);
        friendRequests.setFriendid(friendId);
        friendRequests.setUserid(userId);
        friendRequests.setCreatedat(String.valueOf(System.currentTimeMillis()));
        friendRequests.setMsgread(0);
        friendRequests.setHandle(0);
        return friendRequestsMapper.addFriendRequest(friendRequests) > 0 ;
    }

    @Override
    public boolean updateFriendRequest(FriendRequests friendRequests) {
        return friendRequestsMapper.updateFriendRequest(friendRequests) > 0 ;
    }

    @Override
    public boolean isExistFriendRequest(Integer userId, Integer friendId) {
        return friendRequestsMapper.isExistFriendRequest(userId, friendId) > 0;
    }

}
