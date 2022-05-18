package cn.edu.bjtu.brilley.service.impl;

import cn.edu.bjtu.brilley.dao.AdminMapper;
import cn.edu.bjtu.brilley.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Brilley
 * @date 2022/5/18
 */
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean veritypasswd(String name, String password) {

        return adminMapper.verifyPassword(name, password)>0?true:false;
    }
}
