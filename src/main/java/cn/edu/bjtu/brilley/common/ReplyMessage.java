package cn.edu.bjtu.brilley.common;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Brilley
 * @date 2022/5/24
 */
public class ReplyMessage {
    JSONObject jsonObject = new JSONObject();

    public ReplyMessage(String msgType, String status, String updatedAt, String content, String uuid, String sessionId, Integer talkerId, Integer type) {
        jsonObject.put("msgType", msgType);
        jsonObject.put("status", status);
        jsonObject.put("time", updatedAt);
        jsonObject.put("content", content);
        jsonObject.put("uuid", uuid);
        jsonObject.put("sessionId", sessionId);
        jsonObject.put("talkerId", talkerId);
        jsonObject.put("type", type);
    }
    public JSONObject getMessage() {
        return jsonObject;
    }
}
