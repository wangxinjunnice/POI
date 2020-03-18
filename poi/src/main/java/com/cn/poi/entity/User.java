package com.cn.poi.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author WangXinJun
 * @since 2020-03-16
 */
@TableName("user")
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 性别
     */
    private String sex;
    /**
     * 地址
     */
    private String address;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User{" +
        ", id=" + id +
        ", username=" + username +
        ", birthday=" + birthday +
        ", sex=" + sex +
        ", address=" + address +
        "}";
    }
}
