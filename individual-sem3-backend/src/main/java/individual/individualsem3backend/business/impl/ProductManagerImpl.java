package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.ProductManagerUseCase;
import individual.individualsem3backend.controller.responses.GetAllProductsResponse;
import individual.individualsem3backend.controller.requests.GetAllProductRequest;
import individual.individualsem3backend.domain.Bouquet;
import individual.individualsem3backend.domain.Decoraction;
import individual.individualsem3backend.domain.Flower;
import individual.individualsem3backend.persistence.BouquetRepository;
import individual.individualsem3backend.persistence.DecorationRepository;
import individual.individualsem3backend.persistence.FlowerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductManagerImpl implements ProductManagerUseCase {

    private DecorationRepository decorationRepository;
    private FlowerRepository flowerManagerUseCase;
    private BouquetRepository bouquetRepository;
    @Override
    public GetAllProductsResponse getProducts(final GetAllProductRequest request) {
        List<Flower> resultsFlowers = flowerManagerUseCase.findAll();
        List<Decoraction> resultsDecoration = decorationRepository.findAll();
        List<Bouquet> resultsBouquets = bouquetRepository.findAll();

        //List<Product> resultsAll =

        final GetAllProductsResponse response = new GetAllProductsResponse();

        response.setAllFlowers(resultsFlowers);
        response.setAllDecorations(resultsDecoration);
        response.setAllBouquets(resultsBouquets);

        return response;
    }

}
