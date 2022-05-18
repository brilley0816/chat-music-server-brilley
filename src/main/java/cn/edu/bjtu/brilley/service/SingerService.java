package cn.edu.bjtu.brilley.service;

import cn.edu.bjtu.brilley.domain.Singer;

import java.util.List;

/**
 * @author Brilley
 * @date 2022/5/18
 */
public interface SingerService {
    boolean addSinger (Singer singer);

    boolean updateSingerMsg(Singer singer);

    boolean updateSingerPic(Singer singer);

    boolean deleteSinger(Integer id);

    List<Singer> allSinger();

    List<Singer> singerOfName(String name);

    List<Singer> singerOfSex(Integer sex);
}
