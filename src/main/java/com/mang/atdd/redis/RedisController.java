package com.mang.atdd.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RedisController {
    private final RedisCrudService redisCrudService;
    private final StringRedisTemplate redisTemplate;

    @PostMapping("/save/repository")
    public Long saveByRedisRepository(@RequestBody RedisCrudSaveRequestDto requestDto) {
        return redisCrudService.saveByRedisRepository(requestDto);
    }
}
