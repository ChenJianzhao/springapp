package org.demo.auto.common.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.demo.auto.common.entity.User;
import org.demo.auto.common.redis.RedisCache;
import org.demo.auto.common.redis.RedisEvict;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface UserMapper {

    /**
     * 测试自定义的 Redis 缓存
     * @param id
     * @return
     */
//    @RedisCache(type = User.class)
    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUser(int id);


//    @RedisEvict(type = User.class)
    @Delete("delete from user where id = #{id}")
    int removeUser(int id);

//    @RedisCache(type = User.class)
    List<User> getUserList(List<Integer> ids);

    /**
     * 测试 Spring 支持的缓存
     */
    /**
     *
     * @param user
     * @return
     */
    @CachePut("org.demo.auto.common.entity.User")
    User saveUser(User user);

    @Cacheable(value = "User", key = "#root.targetClass.name.concat(#root.methodName).concat(#root.args)")
    @Select("SELECT * FROM user WHERE id = #{id}")
    User selectUser(int id);

    @CacheEvict(value = "User" , allEntries = true)
    @Delete("delete from user where id = #{id}")
    int deleteUser(int id);

    @CacheEvict(value = "User" , allEntries = true)
    @Update("update user set username = #{username} where id = #{id}")
    void updateUser(User user);
}
