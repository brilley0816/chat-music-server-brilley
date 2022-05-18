package cn.edu.bjtu.brilley.service;

import cn.edu.bjtu.brilley.domain.SongList;

import java.util.List;

/**
 * @author Brilley
 * @date 2022/5/18
 */
public interface SongListService {
    boolean addSongList (SongList songList);

    boolean updateSongListMsg(SongList songList);

    boolean updateSongListImg(SongList songList);

    boolean deleteSongList(Integer id);

    List<SongList> allSongList();

    List<SongList> likeTitle(String title);

    List<SongList> likeStyle(String style);
}

