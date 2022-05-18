package cn.edu.bjtu.brilley.service;

import cn.edu.bjtu.brilley.domain.Song;

import java.util.List;

/**
 * @author Brilley
 * @date 2022/5/18
 */
public interface SongService {
    boolean addSong (Song song);

    boolean updateSongMsg(Song song);

    boolean updateSongUrl(Song song);

    boolean updateSongPic(Song song);

    boolean deleteSong(Integer id);

    List<Song> allSong();

    List<Song> songOfSingerId(Integer singerId);

    List<Song> songOfId(Integer id);

    List<Song> songOfSingerName(String name);
}
