package cn.edu.bjtu.brilley.service;

import cn.edu.bjtu.brilley.domain.ListSong;

import java.util.List;

/**
 * @author Brilley
 * @date 2022/5/18
 */
public interface ListSongService {
    boolean addListSong(ListSong listSong);

    boolean updateListSongMsg(ListSong listSong);

    boolean deleteListSong(Integer songId);

    List<ListSong> allListSong();

    List<ListSong> listSongOfSongId(Integer songListId);
}
