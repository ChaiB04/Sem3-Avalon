package individual.individualsem3backend.persistence;

import individual.individualsem3backend.domain.Decoraction;
import individual.individualsem3backend.domain.Flower;

import java.util.List;

public interface DecorationRepository {
    boolean existsById(Integer productId);

    Decoraction findById(Integer productId);

    Decoraction save(Decoraction product);

    List<Decoraction> findAll();

    void deleteById(Integer productId);
    void update(Decoraction product);
}
