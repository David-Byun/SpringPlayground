package com.mang.atdd.redis;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.time.LocalDateTime;

/*
    RedisRepository 방식과 RedisTemplate 방식이 있음
    RedisRepository 방식의 경우 entity 자체를 저장할 수 있음

    RedisCrudByRepository : Redis Repository 사용할때 사용하는 domain
 */
@Getter
@ToString
//redisCrud를 키로 사용
@RedisHash("redisCrud")
public class RedisCrudByRepository {

    @Id
    private Long id;
    private String description;
    private LocalDateTime updatedAt;

    @Builder
    public RedisCrudByRepository(Long id, String description, LocalDateTime updatedAt) {
        this.id = id;
        this.description = description;
        this.updatedAt = updatedAt;
    }
}
