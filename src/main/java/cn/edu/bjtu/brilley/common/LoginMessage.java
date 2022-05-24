package cn.edu.bjtu.brilley.common;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Brilley
 * @date 2022/5/24
 */
public class LoginMessage {
    JSONObject jsonObject = new JSONObject();

    public LoginMessage(String msgType, String status, String content, String time) {
        jsonObject.put("msgType", msgType);
        jsonObject.put("status", status);
        jsonObject.put("content", content);
        jsonObject.put("time", time);
    }
    public JSONObject getMessage() {
        return jsonObject;
    }
}
