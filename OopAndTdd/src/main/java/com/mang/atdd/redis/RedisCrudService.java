package com.mang.atdd.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RedisCrudService {

    /**
     * 방법1. Redis Repository 사용
     */
    private final RedisCrudRepository redisCrudRepository;

    /**
     * 방법2. Redis Template 사용
     */
    private final StringRedisTemplate redisTemplate;
    private ZSetOperations<String, String> zSetOps;


    // 방법 1
    @Transactional
    public Long saveByRedisRepository(RedisCrudSaveRequestDto requestDto) {
        return redisCrudRepository.save(requestDto.toRedisHash()).getId();
    }

    public RedisByRepositoryResponseDto getByRedisRepository(Long id) {
        RedisCrudByRepository redisCrud = redisCrudRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nothing saved. id = " + id));
        return new RedisByRepositoryResponseDto(redisCrud);
    }

    // 방법 2
    @PostConstruct
    public void init() {
        //opsForZSet : ZSet을 쉽게 Serialize / Deserialize 해주는 interface
        zSetOps = redisTemplate.opsForZSet();
    }

    /**
     * 일반적인 SetOperations은 무질서한 집합
     * 순서가 중요하다면 사용하는 것이 Sorted Sets
     * Sorted Sets는 add 시에 score 를 함께 입력하고, 이를 기반으로 저장을 수행하게 된다. name - key, count - value
     */
    @Transactional
    public Boolean saveByRedisTemplate(String name, double count) {
        Boolean aBoolean = zSetOps.add("redisCrudTemplate", name, count);
        return aBoolean;
    }

    //reverseRange : 0에서 8까지 9개 가져와라 -> API response 형태로 변환시켜줌
    public List<RedisByTemplateResponseDto> get1To9ByRedisTemplate() {
        Set<String> redisCrudTemplate = zSetOps.reverseRange("redisCrudTemplate", 0, 8);
        List<RedisByTemplateResponseDto> redisByTemplateResponseDtoSet = new ArrayList<>(redisCrudTemplate.size());
        Iterator<String> iterator = redisCrudTemplate.iterator();

        int i = 1;
        while (iterator.hasNext()) {
            redisByTemplateResponseDtoSet.add(RedisByTemplateResponseDto.of(i++, iterator.next()));
        }

        return redisByTemplateResponseDtoSet;

    }


}
































