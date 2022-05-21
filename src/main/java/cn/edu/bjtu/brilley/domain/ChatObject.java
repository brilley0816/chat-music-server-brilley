package cn.edu.bjtu.brilley.domain;

import java.io.Serializable;
/**
 * @author Brilley
 * @description voa-server
 * @date 2022-05-19 6:13
 */
public class ChatObject implements Serializable {

    private String username;
    private String data;

    public ChatObject() {}

    public ChatObject(String username, String data) {
        this.username = username;
        this.data = data;
    }
    public String getUserName() {
        return username;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ChatDataEntity{" +
                "username11='" + username + '\'' +
                ", data11='" + data + '\'' +
                '}';
    }
}
