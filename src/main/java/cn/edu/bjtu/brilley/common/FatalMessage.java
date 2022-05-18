package cn.edu.bjtu.brilley.common;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Brilley
 * @date 2022/5/13
 */
public class FatalMessage {
    JSONObject jsonObject = new JSONObject();

    public FatalMessage(String message) {
        jsonObject.put("code", 500);
        jsonObject.put("message", message);
        jsonObject.put("success", false);
        jsonObject.put("type", "error");
        jsonObject.put("data", null);
    }

    public JSONObject getMessage() {
        return jsonObject;
    }
}
