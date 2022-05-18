package cn.edu.bjtu.brilley.service;

import cn.edu.bjtu.brilley.domain.Consumer;

import java.util.List;

/**
 * @author Brilley
 * @date 2022/5/13
 */
public interface ConsumerService {

    boolean addUser (Consumer consumer);

    boolean existUser(String username);

    boolean existPhoneNum(String phone_num);

    boolean verifyPasswd(String username, String password);

    List<Consumer> allUser();

    List<Consumer> loginStatus(String username);

    List<Consumer> userOfId(Integer id);
}
