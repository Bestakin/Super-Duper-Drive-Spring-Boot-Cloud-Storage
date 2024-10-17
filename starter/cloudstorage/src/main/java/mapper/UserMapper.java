package mapper;

import model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;


@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username);
    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);
    @Delete("Delete FROM USERS WHERE username = #{username}")
    User deleteUser(String username);
    @Update(" UPDATE USERS SET (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName}) WHERE username = #{username}")
    User updateUser(User user);
    }

