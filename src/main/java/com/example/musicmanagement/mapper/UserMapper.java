package com.example.musicmanagement.mapper;

import com.example.musicmanagement.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT user_id, username, password, created_at FROM users WHERE username = #{username}")
    User selectUserByUsername(String username);

    @Insert("""
            INSERT INTO users (username, password)
            VALUES (#{username}, #{password})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    void insertUser(User user);
}
