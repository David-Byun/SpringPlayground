package java.redistest.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.redistest.entity.Product;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDao {
    private final RedisTemplate template;
    public static final String HASH_KEY = "Product";

    public Product save(Product product) {
        template.opsForHash().put(HASH_KEY, product.getId(), product);
        return product;
    }

    public List<Product> findAll() {
        return template.opsForHash().values(HASH_KEY);
    }

    public Product findProductById(int id) {
        return (Product) template.opsForHash().get(HASH_KEY, id);
    }

    public String deleteProduct(int id) {
        template.opsForHash().delete(HASH_KEY, id);
        return "product remove!";

    }
}
