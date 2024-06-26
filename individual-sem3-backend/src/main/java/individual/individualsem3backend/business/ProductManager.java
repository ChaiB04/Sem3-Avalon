package individual.individualsem3backend.business;

import individual.individualsem3backend.domain.Product;
import individual.individualsem3backend.domain.ProductFilter;

import java.util.List;

public interface ProductManager {
    List<Product> getProducts(ProductFilter filter);
    Product createProduct(Product request);
    void deleteProduct(Integer productId);

    Product getProduct(Integer productId);
    void updateProduct(Product request);

}
