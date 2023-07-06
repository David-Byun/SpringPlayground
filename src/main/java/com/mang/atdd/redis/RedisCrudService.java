package com.mang.atdd.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RedisCrudService {

    /**
     * 방법1. Redis Repository 사용
     */
    private final RedisCrudRepository redisCrudRepository;


}
