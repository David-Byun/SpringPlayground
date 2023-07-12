package com.mang.atdd.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/*
    Redis 외부 서버가 존재하는 경우는 필요 없지만,
    내장 서버로 환경을 구성할 경우 EmbeddedRedisConfig 설정을 추가로 해야함
 */

@RequiredArgsConstructor
@Profile("local")
@Configuration
public class EmbeddedRedisConfig {
    private final RedisProperties redisProperties;
    private RedisServer redisServer;

    @PostConstruct
    public void redisServer() {
        redisServer = new RedisServer(redisProperties.getPort());
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}
