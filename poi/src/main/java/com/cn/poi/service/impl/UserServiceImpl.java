package com.cn.poi.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.cn.poi.entity.User;
import com.cn.poi.mapper.UserMapper;
import com.cn.poi.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WangXinJun
 * @since 2020-03-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Integer insertByBatch(List<User> users) {
        return baseMapper.insertByBatch(users);
    }

    @Override
    public List<User> listAll() {
        return baseMapper.listAll();
    }
}
