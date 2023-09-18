package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.DecorationManagerUseCase;
import individual.individualsem3backend.business.FlowerManagerUseCase;
import individual.individualsem3backend.business.ProductManagerUseCase;
import individual.individualsem3backend.business.exception.IdAlreadyExistsException;
import individual.individualsem3backend.business.exception.InvalidProductException;
import individual.individualsem3backend.controller.requests.CreateProductRequest;
import individual.individualsem3backend.controller.requests.UpdateProductRequest;
import individual.individualsem3backend.controller.responses.CreateProductResponse;
import individual.individualsem3backend.controller.responses.GetAllProductsResponse;
import individual.individualsem3backend.controller.requests.GetAllProductRequest;
import individual.individualsem3backend.domain.Decoraction;
import individual.individualsem3backend.domain.Flower;
import individual.individualsem3backend.domain.Product;
import individual.individualsem3backend.persistence.DecorationRepository;
import individual.individualsem3backend.persistence.FlowerRepository;
import individual.individualsem3backend.persistence.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductManagerImpl implements ProductManagerUseCase {

    private DecorationRepository decorationRepository;
    private FlowerRepository flowerManagerUseCase;
    @Override
    public GetAllProductsResponse getProducts(final GetAllProductRequest request) {
        List<Flower> resultsFlowers = flowerManagerUseCase.findAll();
        List<Decoraction> resultsDecoration = decorationRepository.findAll();

        //List<Product> resultsAll =

        final GetAllProductsResponse response = new GetAllProductsResponse();

        response.setAllFlowers(resultsFlowers);
        response.setAllDecorations(resultsDecoration);

        return response;
    }

}
