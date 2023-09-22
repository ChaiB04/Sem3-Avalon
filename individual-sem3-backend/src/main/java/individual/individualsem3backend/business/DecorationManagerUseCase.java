package individual.individualsem3backend.business;

import individual.individualsem3backend.controller.DecorationRequestResponse.*;
import individual.individualsem3backend.domain.Decoraction;

import java.util.List;
import java.util.Optional;

public interface DecorationManagerUseCase {
    List<Decoraction> getProducts();
    Decoraction createProduct(Decoraction request);
    void deleteProduct(Integer productId);

    Optional<Decoraction> getProduct(Integer productId);
    void updateProduct(Decoraction request);
}
