package cn.edu.bjtu.brilley.domain;

public class FriendRequests {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_requests.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_requests.userId
     *
     * @mbggenerated
     */
    private Integer userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_requests.friendId
     *
     * @mbggenerated
     */
    private Integer friendid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_requests.content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_requests.read
     *
     * @mbggenerated
     */
    private Integer msgread;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_requests.handle
     *
     * @mbggenerated
     */
    private Integer handle;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_requests.createdAt
     *
     * @mbggenerated
     */
    private String createdat;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_requests.id
     *
     * @return the value of friend_requests.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_requests.id
     *
     * @param id the value for friend_requests.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_requests.userId
     *
     * @return the value of friend_requests.userId
     *
     * @mbggenerated
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_requests.userId
     *
     * @param userid the value for friend_requests.userId
     *
     * @mbggenerated
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_requests.friendId
     *
     * @return the value of friend_requests.friendId
     *
     * @mbggenerated
     */
    public Integer getFriendid() {
        return friendid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_requests.friendId
     *
     * @param friendid the value for friend_requests.friendId
     *
     * @mbggenerated
     */
    public void setFriendid(Integer friendid) {
        this.friendid = friendid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_requests.content
     *
     * @return the value of friend_requests.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_requests.content
     *
     * @param content the value for friend_requests.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_requests.read
     *
     * @return the value of friend_requests.read
     *
     * @mbggenerated
     */
    public Integer getMsgread() {
        return msgread;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_requests.read
     *
     * @param read the value for friend_requests.read
     *
     * @mbggenerated
     */
    public void setMsgread(Integer msgread) {
        this.msgread = msgread;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_requests.handle
     *
     * @return the value of friend_requests.handle
     *
     * @mbggenerated
     */
    public Integer getHandle() {
        return handle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_requests.handle
     *
     * @param handle the value for friend_requests.handle
     *
     * @mbggenerated
     */
    public void setHandle(Integer handle) {
        this.handle = handle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_requests.createdAt
     *
     * @return the value of friend_requests.createdAt
     *
     * @mbggenerated
     */
    public String getCreatedat() {
        return createdat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_requests.createdAt
     *
     * @param createdat the value for friend_requests.createdAt
     *
     * @mbggenerated
     */
    public void setCreatedat(String createdat) {
        this.createdat = createdat == null ? null : createdat.trim();
    }
}