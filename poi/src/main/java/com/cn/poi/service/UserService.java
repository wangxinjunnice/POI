package com.cn.poi.service;


import com.baomidou.mybatisplus.service.IService;
import com.cn.poi.entity.User;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WangXinJun
 * @since 2020-03-16
 */
public interface UserService extends IService<User> {
    Integer insertByBatch(List<User> users);
    List<User> listAll();

}
