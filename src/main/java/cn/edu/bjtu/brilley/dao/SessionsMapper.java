package cn.edu.bjtu.brilley.dao;

import cn.edu.bjtu.brilley.domain.Sessions;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface SessionsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sessions
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sessions
     *
     * @mbggenerated
     */
    int insert(Sessions record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sessions
     *
     * @mbggenerated
     */
    int insertSelective(Sessions record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sessions
     *
     * @mbggenerated
     */
    Sessions selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sessions
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Sessions record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sessions
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Sessions record);

    /**
     * 返回一个用户的所有会话
     * @param userId
     * @param lastTime
     * @return
     */
    List<Sessions> getSessionList(String userId, String lastTime);

    /**
     * 判断会话是否存在
     * @param userId
     * @param receiverId
     * @return
     */
    int getSessionExist(String userId, String receiverId);

    /**
     * 创建一个会话
     * @param sessions
     * @return
     */
    int createSession(Sessions sessions);

    /**
     * 更新会话内容和数量，并更新时间
     * @param sessions
     * @return
     */
    int updateSession(Sessions sessions);

}