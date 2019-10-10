package com.jason.mapper;

import com.jason.model.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    @Select("select * from user where username = #{username} and password = #{password}")
    User selectUserByUserNameAndPassowrd(User u);
}
