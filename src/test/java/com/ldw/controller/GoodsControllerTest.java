package com.ldw.controller;

import com.ldw.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GoodsControllerTest {
  @Autowired
  private RedisTemplate redisTemplate;
  @Autowired
  private RedisUtil redisUtil;
    @Test
    void newHotGoodsList() {
      //  redisTemplate.setValueSerializer(new StringRedisSerializer());
       // Object o=redisTemplate.opsForValue().get("NewHotGoodsList");
      redisTemplate.setValueSerializer(new StringRedisSerializer());

      Object o=redisUtil.get("NewHotGoodsList");
        System.out.println(o);
    }

    @Test
    void goodList() {
     //   Object o=redisTemplate.opsForValue().get("GoodsList");
       // System.out.println(o);
    }
}