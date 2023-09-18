package individual.individualsem3backend.business;

import individual.individualsem3backend.controller.DecorationRequestResponse.*;
import individual.individualsem3backend.domain.Decoraction;
import java.util.Optional;

public interface DecorationManagerUseCase {
    GetAllDecorationResponse getProducts(GetAllDecorationRequest request);
    CreateDecorationResponse createProduct(CreateDecorationRequest request);
    void deleteProduct(Integer productId);

    Optional<Decoraction> getProduct(Integer productId);
    void updateProduct(UpdateDecorationRequest request);
}
