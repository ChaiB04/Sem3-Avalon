package individual.individualsem3backend.business;

import individual.individualsem3backend.controller.BouquetRequestResponse.*;
import individual.individualsem3backend.domain.Bouquet;

import java.util.List;
import java.util.Optional;

public interface BouquetManagerUseCase {
    List<Bouquet> getProducts();
    Bouquet createProduct(Bouquet request);
    void deleteProduct(Integer productId);

    Optional<Bouquet> getProduct(Integer productId);
    void updateProduct(Bouquet request);
}
