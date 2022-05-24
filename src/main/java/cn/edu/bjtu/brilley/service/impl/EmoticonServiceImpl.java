package cn.edu.bjtu.brilley.service.impl;

import cn.edu.bjtu.brilley.dao.EmoticonsMapper;
import cn.edu.bjtu.brilley.domain.Emoticons;
import cn.edu.bjtu.brilley.service.EmoticonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Brilley
 * @date 2022/5/23
 */
@Service
public class EmoticonServiceImpl implements EmoticonService {

    @Autowired
    EmoticonsMapper emoticonsMapper;

    @Override
    public List<Emoticons> getEmoticonList(Integer userId) {
        return emoticonsMapper.getEmoticonList(userId);
    }

    @Override
    public Integer addEmoticon(Integer userId, String srcStr) {
        Emoticons emoticons = new Emoticons();
        emoticons.setCreatedat(String.valueOf(System.currentTimeMillis()));
        emoticons.setUserid(userId);
        emoticons.setSrc(srcStr);
        return emoticonsMapper.addEmoticon(emoticons);
    }
}
