package individual.individualsem3backend.persistence.impl;

import individual.individualsem3backend.domain.Product;
import individual.individualsem3backend.persistence.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
public class fakeProductRepositoryImpl implements ProductRepository {
    private static int NEXT_ID = 1;

    private final List<Product> savedProducts;

    public fakeProductRepositoryImpl() {
        this.savedProducts = new ArrayList<>();
    }

    @Override
    public boolean existsById(int productId) {
        return this.savedProducts
                .stream()
                .anyMatch(productEntity -> productEntity.getId() == productId);
    }

    @Override
    public Product findById(int productId) {
        return this.savedProducts
                .stream()
                .filter(productEntity -> productEntity.getId() == productId)
                .findFirst()
                .orElse(null);
    }


    @Override
    public void update(Product product){
        savedProducts.set(product.getId() -1 , product);
    }

    @Override
    public Product save(Product product) {
        product.setId(NEXT_ID);
        NEXT_ID++;
        this.savedProducts.add(product);
        return product;
    }

    @Override
    public List<Product> findAll() {

        return Collections.unmodifiableList(savedProducts);
    }

    @Override
    public void deleteById(int productId) {
        this.savedProducts.removeIf(product -> product.getId() == productId);
    }
}
