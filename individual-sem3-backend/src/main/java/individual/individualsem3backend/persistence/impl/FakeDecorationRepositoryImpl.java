package individual.individualsem3backend.persistence.impl;

import individual.individualsem3backend.domain.Decoraction;
import individual.individualsem3backend.domain.Flower;
import individual.individualsem3backend.persistence.DecorationRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Repository
public class FakeDecorationRepositoryImpl implements DecorationRepository {
    private static Integer NEXT_ID = 1;

    private final List<Decoraction> savedProducts;

    public FakeDecorationRepositoryImpl() {
        this.savedProducts = new ArrayList<>();
    }

    @Override
    public boolean existsByName(String productName){
        return this.savedProducts
                .stream()
                .anyMatch(productEntity -> productEntity.getName() == productName);
    }

    @Override
    public Decoraction findById(Integer productId) {
        return this.savedProducts
                .stream()
                .filter(productEntity -> productEntity.getId() == productId)
                .findFirst()
                .orElse(null);
    }


    @Override
    public void update(Decoraction product){
        //savedProducts.set(product.getId() -1 , product);
    }

    @Override
    public Decoraction save(Decoraction product) {
        product.setId(NEXT_ID);
        NEXT_ID++;
        this.savedProducts.add(product);
        return product;
    }

    @Override
    public List<Decoraction> findAll() {

        return Collections.unmodifiableList(savedProducts);
    }

    @Override
    public void deleteById(Integer productId) {
        this.savedProducts.removeIf(product -> product.getId() == productId);
    }

}
