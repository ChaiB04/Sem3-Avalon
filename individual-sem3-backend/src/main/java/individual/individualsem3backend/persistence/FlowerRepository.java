package individual.individualsem3backend.persistence;

import individual.individualsem3backend.domain.Flower;
import individual.individualsem3backend.domain.Product;

import java.util.List;

public interface FlowerRepository {

    boolean existsByName(String productName);

    Flower findById(Integer productId);

    Flower save(Flower product);

    List<Flower> findAll();

    void deleteById(Integer productId);
    void update(Flower product);
}
