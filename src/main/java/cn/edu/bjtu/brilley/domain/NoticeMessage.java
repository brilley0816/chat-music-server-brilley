package cn.edu.bjtu.brilley.domain;

/**
 * @author Brilley
 * @date 2022/5/20
 */
public class NoticeMessage {
    String msgType;
    String status;
    String content;
    int time;

    public NoticeMessage(String msgType, String status, String content, int time) {
        this.msgType = msgType;
        this.status = status;
        this.content = content;
        this.time = time;
    }

    @Override
    public String toString() {
        return "{" +
                "\"msgType\":\"" + msgType + '\"' +
                ", \"status\":\"" + status + '\"' +
                ", \"content\":\"" + content + '\"' +
                ", \"time=\":\"" + time + '\"' +
                "}";
    }
}
