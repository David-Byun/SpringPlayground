package com.mang.atdd.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RedisController {
    private final RedisCrudService redisCrudService;
}
