package cn.edu.bjtu.brilley.domain;

public class RankList {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rank_list.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rank_list.songListId
     *
     * @mbggenerated
     */
    private Long songlistid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rank_list.consumerId
     *
     * @mbggenerated
     */
    private Long consumerid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rank_list.score
     *
     * @mbggenerated
     */
    private Integer score;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rank_list.id
     *
     * @return the value of rank_list.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rank_list.id
     *
     * @param id the value for rank_list.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rank_list.songListId
     *
     * @return the value of rank_list.songListId
     *
     * @mbggenerated
     */
    public Long getSonglistid() {
        return songlistid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rank_list.songListId
     *
     * @param songlistid the value for rank_list.songListId
     *
     * @mbggenerated
     */
    public void setSonglistid(Long songlistid) {
        this.songlistid = songlistid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rank_list.consumerId
     *
     * @return the value of rank_list.consumerId
     *
     * @mbggenerated
     */
    public Long getConsumerid() {
        return consumerid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rank_list.consumerId
     *
     * @param consumerid the value for rank_list.consumerId
     *
     * @mbggenerated
     */
    public void setConsumerid(Long consumerid) {
        this.consumerid = consumerid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rank_list.score
     *
     * @return the value of rank_list.score
     *
     * @mbggenerated
     */
    public Integer getScore() {
        return score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rank_list.score
     *
     * @param score the value for rank_list.score
     *
     * @mbggenerated
     */
    public void setScore(Integer score) {
        this.score = score;
    }
}