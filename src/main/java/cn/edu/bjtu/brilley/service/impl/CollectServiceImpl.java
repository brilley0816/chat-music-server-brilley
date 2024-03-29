package cn.edu.bjtu.brilley.service.impl;

import cn.edu.bjtu.brilley.dao.CollectMapper;
import cn.edu.bjtu.brilley.domain.Collect;
import cn.edu.bjtu.brilley.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Brilley
 * @description voa-server
 * @date 2022-05-19 3:07
 */
@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public boolean addCollection(Collect collect) {
        return collectMapper.insertSelective(collect) > 0 ? true : false;
    }

    @Override
    public boolean existSongId(Integer userId, Integer songId) {
        return collectMapper.existSongId(userId, songId) > 0 ? true : false;
    }

    @Override
    public boolean deleteCollect(Integer userId, Integer songId) {
        return collectMapper.deleteCollect(userId, songId) > 0 ? true : false;
    }

    @Override
    public List<Collect> collectionOfUser(Integer userId)

    {
        return collectMapper.collectionOfUser(userId);
    }
}
