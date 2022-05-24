package cn.edu.bjtu.brilley.common;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Brilley
 * @date 2022/5/23
 */
public class ChatMessage {
    JSONObject jsonObject = new JSONObject();

    public ChatMessage(String content, String time, String sessionId, Integer talkerId, Integer type) {
        jsonObject.put("content", content);
        jsonObject.put("time", time);
        jsonObject.put("sessionId", sessionId);
        jsonObject.put("talkerId", talkerId);
        jsonObject.put("type", type);
    }
    public JSONObject getMessage() {
        return jsonObject;
    }
}
