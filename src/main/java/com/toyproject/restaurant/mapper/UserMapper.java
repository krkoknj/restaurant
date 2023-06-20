package com.toyproject.restaurant.mapper;

import com.toyproject.restaurant.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USERS")
    List<User> findAllUsers();

    @Insert("INSERT INTO USERS (username, email) VALUES (#{username}, #{email})")
    @SelectKey(statement="call identity()", keyProperty="id", before=false, resultType=Integer.class)
    Integer insertUsernameAndEmail(User user);
}
