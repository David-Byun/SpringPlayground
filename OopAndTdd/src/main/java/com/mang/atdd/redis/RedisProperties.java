package com.mang.atdd.redis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
    properties에 작성해둔 host, post를 받아와 객체 클래스로 제작
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {

    private String host;
    private int port;
}
