package individual.individualsem3backend.business;

import individual.individualsem3backend.controller.DecorationRequestResponse.*;
import individual.individualsem3backend.controller.requests.CreateProductRequest;
import individual.individualsem3backend.controller.requests.GetAllProductRequest;
import individual.individualsem3backend.controller.requests.UpdateProductRequest;
import individual.individualsem3backend.controller.responses.CreateProductResponse;
import individual.individualsem3backend.controller.responses.GetAllProductsResponse;
import individual.individualsem3backend.domain.Decoraction;
import individual.individualsem3backend.domain.Product;

import java.util.Optional;

public interface DecorationManagerUseCase {
    GetAllDecorationResponse getProducts(GetAllDecorationRequest request);
    CreateDecorationResponse createProduct(CreateDecorationRequest request);
    void deleteProduct(Integer productId);

    Optional<Decoraction> getProduct(Integer productId);
    void updateProduct(UpdateDecorationRequest request);
}
