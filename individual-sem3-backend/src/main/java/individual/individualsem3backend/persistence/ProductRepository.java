package individual.individualsem3backend.persistence;

import individual.individualsem3backend.domain.Product;

import java.util.List;

public interface ProductRepository {

    boolean existsByName(String productName);

    Product findById(Integer productId);

    Product save(Product product);

    List<Product> findAll();

    void deleteById(Integer productId);
    void update(Product product);
}
