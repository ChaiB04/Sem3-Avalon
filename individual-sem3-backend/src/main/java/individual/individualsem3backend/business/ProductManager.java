package individual.individualsem3backend.business;

import individual.individualsem3backend.domain.Product;

import java.util.*;

public interface ProductManager {
    List<Product> getProducts();
    Product createProduct(Product request);
    void deleteProduct(int productId);

    Optional<Product> getProduct(int productId);
    void updateProduct(Product request);

}
