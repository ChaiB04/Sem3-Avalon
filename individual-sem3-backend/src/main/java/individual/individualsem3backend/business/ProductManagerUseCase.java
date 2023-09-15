package individual.individualsem3backend.business;

import individual.individualsem3backend.controller.requests.CreateProductRequest;
import individual.individualsem3backend.controller.requests.GetAllProductRequest;
import individual.individualsem3backend.controller.requests.UpdateProductRequest;
import individual.individualsem3backend.controller.responses.CreateProductResponse;
import individual.individualsem3backend.controller.responses.GetAllProductsResponse;
import individual.individualsem3backend.domain.Product;

import java.util.Optional;

public interface ProductManagerUseCase {
    GetAllProductsResponse getProducts(GetAllProductRequest request);
    CreateProductResponse createProduct(CreateProductRequest request);
    void deleteProduct(int productId);

    Optional<Product> getProduct(int productId);
    void updateProduct(UpdateProductRequest request);
}
