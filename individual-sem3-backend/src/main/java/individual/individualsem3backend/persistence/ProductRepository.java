package individual.individualsem3backend.persistence;

import individual.individualsem3backend.domain.Product;

import java.util.List;

public interface ProductRepository {

    boolean existsById(int productId);

    Product findById(int productId);

    Product save(Product product);

    List<Product> findAll();

    int count();
}
