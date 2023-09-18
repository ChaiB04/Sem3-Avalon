package individual.individualsem3backend.business;

import individual.individualsem3backend.controller.BouquetRequestResponse.*;
import individual.individualsem3backend.domain.Bouquet;

import java.util.Optional;

public interface BouquetManagerUseCase {
    GetAllBouquetResponse getProducts(GetAllBouquetRequest request);
    CreateBouquetResponse createProduct(CreateBouquetRequest request);
    void deleteProduct(Integer productId);

    Optional<Bouquet> getProduct(Integer productId);
    void updateProduct(UpdateBouquetRequest request);
}
