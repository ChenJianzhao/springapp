package org.demo.auto.common.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.demo.auto.common.entity.User;
import org.demo.auto.common.redis.RedisCache;
import org.demo.auto.common.redis.RedisEvict;

import java.util.List;

public interface UserMapper {

    @RedisCache(type = User.class)
    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUser(int id);


    @RedisEvict(type = User.class)
    @Delete("delete from user where id = #{id}")
    int deleteUser(int id);

    @RedisCache(type = List.class)
    List<User> getUserList(List<Integer> ids);
}
