package cn.edu.bjtu.brilley.service.impl;

import cn.edu.bjtu.brilley.dao.ConsumerMapper;
import cn.edu.bjtu.brilley.domain.Consumer;
import cn.edu.bjtu.brilley.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Brilley
 * @date 2022/5/13
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {

    // dao mapper 映射了对数据库的操作行为
    @Autowired
    private ConsumerMapper consumerMapper;

    @Override
    public boolean addUser(Consumer consumer) {
        return consumerMapper.addUser(consumer) > 0 ? true : false;
    }

    @Override
    public boolean existUser(String username) {
        return consumerMapper.existUsername(username) > 0;
    }

    @Override
    public boolean existPhoneNum(String phone_num) {
        return consumerMapper.existPhoneNum(phone_num) > 0;
    }

    @Override
    public boolean verifyPasswd(String username, String password) {
        return consumerMapper.verifyPassword(username, password) > 0;
    }

    @Override
    public List<Consumer> allUser() {
        return consumerMapper.allUser();
    }

    @Override
    public List<Consumer> loginStatus(String username) {
        return consumerMapper.loginStatus(username);
    }

    @Override
    public List<Consumer> userOfId(Integer id) {return consumerMapper.userOfId(id);}
}
