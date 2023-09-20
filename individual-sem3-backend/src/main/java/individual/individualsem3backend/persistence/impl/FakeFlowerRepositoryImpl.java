package individual.individualsem3backend.persistence.impl;

import individual.individualsem3backend.domain.Flower;
import individual.individualsem3backend.domain.Product;
import individual.individualsem3backend.persistence.FlowerRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class FakeFlowerRepositoryImpl implements FlowerRepository {
    private static Integer NEXT_ID = 1;

    private final List<Flower> savedProducts;

    public FakeFlowerRepositoryImpl() {
        this.savedProducts = new ArrayList<>();
    }

    @Override
    public boolean existsByName(String productName){
        return this.savedProducts
                .stream()
                .anyMatch(productEntity -> productEntity.getName() == productName);
    }

    @Override
    public Flower findById(Integer productId) {
        return this.savedProducts
                .stream()
                .filter(productEntity -> productEntity.getId() == productId)
                .findFirst()
                .orElse(null);
    }


    @Override
    public void update(Flower product){
//        savedProducts.set(product.getId() - 1 , product);
//        savedProducts.
    }

    @Override
    public Flower save(Flower product) {
        product.setId(NEXT_ID);
        NEXT_ID++;
        this.savedProducts.add(product);
        return product;
    }

    @Override
    public List<Flower> findAll() {

        return Collections.unmodifiableList(savedProducts);
    }

    @Override
    public void deleteById(Integer productId) {
        this.savedProducts.removeIf(product -> product.getId() == productId);
    }


}
