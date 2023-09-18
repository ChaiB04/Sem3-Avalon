package individual.individualsem3backend.business;

import individual.individualsem3backend.controller.requests.GetAllProductRequest;
import individual.individualsem3backend.controller.responses.GetAllProductsResponse;

public interface ProductManagerUseCase {
    GetAllProductsResponse getProducts(GetAllProductRequest request);
}
