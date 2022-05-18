package cn.edu.bjtu.brilley.common;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Brilley
 * @date 2022/5/13
 */
public class ErrorMessage {
    JSONObject jsonObject = new JSONObject();

    public ErrorMessage(String message) {
        jsonObject.put("code", 200);
        jsonObject.put("message", message);
        jsonObject.put("success", false);
        jsonObject.put("type", "error");
        jsonObject.put("data", null);
    }

    public JSONObject getMessage() {
        return jsonObject;
    }
}
