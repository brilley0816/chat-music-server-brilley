package cn.edu.bjtu.brilley.domain;

public class Notifications {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notifications.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notifications.userId
     *
     * @mbggenerated
     */
    private Integer userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notifications.type
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notifications.createdAt
     *
     * @mbggenerated
     */
    private String createdat;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notifications.id
     *
     * @return the value of notifications.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notifications.id
     *
     * @param id the value for notifications.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notifications.userId
     *
     * @return the value of notifications.userId
     *
     * @mbggenerated
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notifications.userId
     *
     * @param userid the value for notifications.userId
     *
     * @mbggenerated
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notifications.type
     *
     * @return the value of notifications.type
     *
     * @mbggenerated
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notifications.type
     *
     * @param type the value for notifications.type
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notifications.createdAt
     *
     * @return the value of notifications.createdAt
     *
     * @mbggenerated
     */
    public String getCreatedat() {
        return createdat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notifications.createdAt
     *
     * @param createdat the value for notifications.createdAt
     *
     * @mbggenerated
     */
    public void setCreatedat(String createdat) {
        this.createdat = createdat == null ? null : createdat.trim();
    }
}