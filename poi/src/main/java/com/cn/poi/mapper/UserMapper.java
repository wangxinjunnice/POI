package com.cn.poi.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cn.poi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WangXinJun
 * @since 2020-03-16
 */
public interface UserMapper extends BaseMapper<User> {

    Integer insertByBatch(@Param("list") List<User> users);

    List<User> listAll();

}
