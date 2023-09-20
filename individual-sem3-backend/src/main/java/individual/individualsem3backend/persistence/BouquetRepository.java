package individual.individualsem3backend.persistence;

import individual.individualsem3backend.domain.Bouquet;
import individual.individualsem3backend.domain.Decoraction;

import java.util.List;

public interface BouquetRepository {
    boolean existsByName(String productName);

    Bouquet findById(Integer productId);

    Bouquet save(Bouquet product);

    List<Bouquet> findAll();

    void deleteById(Integer productId);
    void update(Bouquet product);
}
