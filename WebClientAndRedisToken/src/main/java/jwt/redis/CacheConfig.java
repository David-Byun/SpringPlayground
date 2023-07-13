package jwt.redis;


import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/*
    1. 로그인(accessToken을 발급 받고, refreshToken을 redis에 저장)
    2. 로그아웃(accessToken의 남은 유효기간 동안 redis에 logoutAccessToken을 저장하여 해당 토큰으로 접근하는 것을 금지시키기)
    3. 토큰 재발급(토큰의 유효기간이 있기 때문에 기간이 지났을 경우를 위해 redis에 저장한 refreshToken의 남은 만료 기간에 따라 accessToken과 refreshToken 두개 모두 혹은 accessToken만 재발급하기 위해)
 */
@Configuration
@RequiredArgsConstructor
@EnableCaching //스프링 캐시를 활성화하는 역할 : 메소드 결과를 캐시에 저장하고 재사용할 수 있음
public class CacheConfig {

    @Bean
    public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig() //defaultCacheConfig() 메소드를 호출하여 기본적인 Redis 캐시 설정 가져옴
                .disableCachingNullValues() // 캐시에 null값을 저장하지 않도록 설정. null값을 반환하는 메소드 결과를 캐시에 저장하지 않음(캐싱시 unless("#result == null") 필수)
                .entryTtl(Duration.ofSeconds(CacheKey.DEFAULT_EXPIRE_SEC)) //캐시 항목의 만료시간을 설정할 수 있음
                .computePrefixWith(CacheKeyPrefix.simple()) // name::key 처럼 key 앞에 '::'를 삽입(redis-cli에서 get "name::key" 로 조회)
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())) //Redis는 문자열로 키를 저장하므로, 키를 문자열로 직렬화하여 Redis에 저장
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())); //캐시값(객체)을 직렬화하기 위해 사용. 객체를 JSON 문자열로 직렬화하여 Redis에 저장

        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory)
                .cacheDefaults(configuration)
                .build();
    }
}
