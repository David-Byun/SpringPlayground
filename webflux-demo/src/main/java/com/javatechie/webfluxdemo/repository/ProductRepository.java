package com.javatechie.webfluxdemo.repository;

import com.javatechie.webfluxdemo.dto.ProductDto;
import com.javatechie.webfluxdemo.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Flux<ProductDto> findByPriceBetween(Range<Double> priceRange);

}
