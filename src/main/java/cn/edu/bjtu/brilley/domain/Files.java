package cn.edu.bjtu.brilley.domain;

public class Files {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column files.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column files.userId
     *
     * @mbggenerated
     */
    private Integer userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column files.fileName
     *
     * @mbggenerated
     */
    private String filename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column files.src
     *
     * @mbggenerated
     */
    private String src;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column files.size
     *
     * @mbggenerated
     */
    private Integer size;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column files.createdAt
     *
     * @mbggenerated
     */
    private String createdat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column files.type
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column files.id
     *
     * @return the value of files.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column files.id
     *
     * @param id the value for files.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column files.userId
     *
     * @return the value of files.userId
     *
     * @mbggenerated
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column files.userId
     *
     * @param userid the value for files.userId
     *
     * @mbggenerated
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column files.fileName
     *
     * @return the value of files.fileName
     *
     * @mbggenerated
     */
    public String getFilename() {
        return filename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column files.fileName
     *
     * @param filename the value for files.fileName
     *
     * @mbggenerated
     */
    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column files.src
     *
     * @return the value of files.src
     *
     * @mbggenerated
     */
    public String getSrc() {
        return src;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column files.src
     *
     * @param src the value for files.src
     *
     * @mbggenerated
     */
    public void setSrc(String src) {
        this.src = src == null ? null : src.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column files.size
     *
     * @return the value of files.size
     *
     * @mbggenerated
     */
    public Integer getSize() {
        return size;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column files.size
     *
     * @param size the value for files.size
     *
     * @mbggenerated
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column files.createdAt
     *
     * @return the value of files.createdAt
     *
     * @mbggenerated
     */
    public String getCreatedat() {
        return createdat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column files.createdAt
     *
     * @param createdat the value for files.createdAt
     *
     * @mbggenerated
     */
    public void setCreatedat(String createdat) {
        this.createdat = createdat == null ? null : createdat.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column files.type
     *
     * @return the value of files.type
     *
     * @mbggenerated
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column files.type
     *
     * @param type the value for files.type
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
        this.type = type;
    }
}