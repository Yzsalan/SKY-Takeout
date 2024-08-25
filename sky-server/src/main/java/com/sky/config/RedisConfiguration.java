package com.sky.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfiguration {

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){//参数 RedisConnectionFactory 是 Redis 连接工厂，它由 Spring 自动注入，用于创建 Redis 连接。
        log.info("开始创建redis模板对象...");
        //RedisTemplate 是 Spring 提供的一个泛型类，封装了与 Redis 交互的常用操作。
        RedisTemplate redisTemplate = new RedisTemplate();
        //设置redis的连接工厂对象
        //RedisConnectionFactory 提供了与 Redis 服务器的连接信息，比如主机地址、端口、密码等。
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置redis key的序列化器
        //Redis 中的 key 和 value 都是二进制数据，Spring 提供了多种序列化器来将 Java 对象转换为 Redis 可接受的格式。StringRedisSerializer 将 key 序列化为字符串形式。
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
