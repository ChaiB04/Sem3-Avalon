package individual.individualsem3backend.business;

import individual.individualsem3backend.controller.ProductRequestResponse.GetAllProductRequest;
import individual.individualsem3backend.controller.ProductRequestResponse.GetAllProductsResponse;

public interface ProductManagerUseCase {
    GetAllProductsResponse getProducts(GetAllProductRequest request);
}
