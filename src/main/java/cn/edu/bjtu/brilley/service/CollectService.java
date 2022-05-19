package cn.edu.bjtu.brilley.service;

import cn.edu.bjtu.brilley.domain.Collect;

import java.util.List;

/**
 * @author Brilley
 * @description voa-server
 * @date 2022-05-19 3:06
 */
public interface CollectService {
    boolean addCollection(Collect collect);

    boolean existSongId(Integer userId, Integer songId);

    boolean deleteCollect(Integer userId, Integer songId);

    List<Collect> collectionOfUser(Integer userId);
}
