package com.mang.atdd.oop.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
}
