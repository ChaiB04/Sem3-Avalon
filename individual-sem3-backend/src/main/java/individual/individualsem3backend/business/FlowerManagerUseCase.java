package individual.individualsem3backend.business;

import individual.individualsem3backend.controller.FlowerRequestResponse.*;
import individual.individualsem3backend.domain.Flower;


import java.util.List;
import java.util.Optional;

public interface FlowerManagerUseCase {
    List<Flower> getProducts(String color);
    Flower createProduct(Flower request);
    void deleteProduct(Integer productId);

    Optional<Flower> getProduct(Integer productId);
    void updateProduct(Flower request);
}
