package cn.edu.bjtu.brilley.service;

import cn.edu.bjtu.brilley.domain.Emoticons;

import java.util.List;

/**
 * @author Brilley
 * @date 2022/5/23
 */
public interface EmoticonService {

    List<Emoticons> getEmoticonList(Integer userId);

    Integer addEmoticon(Integer userId, String srcStr);
}
