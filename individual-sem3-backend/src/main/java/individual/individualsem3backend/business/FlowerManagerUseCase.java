package individual.individualsem3backend.business;

import individual.individualsem3backend.controller.FlowerRequestResponse.*;
import individual.individualsem3backend.controller.requests.CreateProductRequest;
import individual.individualsem3backend.domain.Flower;


import java.util.Optional;

public interface FlowerManagerUseCase {
    GetAllFlowerResponse getProducts(GetAllFlowerRequest request);
    CreateFlowerResponse createProduct(CreateFlowerRequest request);
    void deleteProduct(Integer productId);

    Optional<Flower> getProduct(Integer productId);
    void updateProduct(UpdateFlowerRequest request);
}
