package com.mang.atdd.redis;

import org.springframework.data.repository.CrudRepository;

public interface RedisCrudRepository extends CrudRepository<RedisCrudByRepository, Long> {
}
