package lyr.redis.utils;

import lyr.redis.RedisApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes={RedisApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)// 指定启动类
@ContextConfiguration
public class RedisUtilTest {

    @Resource
    RedisUtil redisUtil;

    @Test
    public void expire() {
        redisUtil.expire("ReidsTest",10);
    }

    @Test
    public void getExpire() {
        redisUtil.getExpire("test");
    }

    @Test
    public void hasKey() {
    }

    @Test
    public void del() {
    }

    @Test
    public void get() {
    }

    @Test
    public void set() {
        redisUtil.set("test","lyr");
    }

    @Test
    public void set1() {
    }

    @Test
    public void incr() {
    }

    @Test
    public void decr() {
    }

    @Test
    public void hget() {
    }

    @Test
    public void hmget() {
    }

    @Test
    public void hmset() {
    }

    @Test
    public void hmset1() {
    }

    @Test
    public void hset() {
    }

    @Test
    public void hset1() {
    }

    @Test
    public void hdel() {
    }

    @Test
    public void hHasKey() {
    }

    @Test
    public void hincr() {
    }

    @Test
    public void hdecr() {
    }

    @Test
    public void sGet() {
    }

    @Test
    public void sHasKey() {
    }

    @Test
    public void sSet() {
    }

    @Test
    public void sSetAndTime() {
    }

    @Test
    public void sGetSetSize() {
    }

    @Test
    public void setRemove() {
    }

    @Test
    public void lGet() {
    }

    @Test
    public void lGetListSize() {
    }

    @Test
    public void lGetIndex() {
    }

    @Test
    public void lSet() {
    }

    @Test
    public void lSet1() {
    }

    @Test
    public void lSet2() {
    }

    @Test
    public void lSet3() {
    }

    @Test
    public void lUpdateIndex() {
    }

    @Test
    public void lRemove() {
    }
}