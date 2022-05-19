package cn.edu.bjtu.brilley.domain;

import java.io.Serializable;
/**
 * @author Brilley
 * @description voa-server
 * @date 2022-05-19 6:13
 */
public class ChatObject implements Serializable {

    private String username;
    private String message;

    public ChatObject() {}

    public ChatObject(String username, String message) {
        this.username = username;
        this.message = message;
    }
    public String getUserName() {
        return username;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ChatDataEntity{" +
                "username11='" + username + '\'' +
                ", message11='" + message + '\'' +
                '}';
    }
}
