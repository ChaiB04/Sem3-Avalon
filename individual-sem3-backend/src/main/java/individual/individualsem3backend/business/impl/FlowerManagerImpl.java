package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.FlowerManagerUseCase;
import individual.individualsem3backend.business.exception.IdAlreadyExistsException;
import individual.individualsem3backend.controller.FlowerRequestResponse.*;
import individual.individualsem3backend.domain.Flower;
import individual.individualsem3backend.persistence.FlowerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class FlowerManagerImpl implements FlowerManagerUseCase {
    private FlowerRepository flowerRepository;

    @Override
    public GetAllFlowerResponse getProducts(final GetAllFlowerRequest request) {
        List<Flower> results = flowerRepository.findAll();

        final GetAllFlowerResponse response = new GetAllFlowerResponse();

        response.setAllFlowers(results);

        return response;
    }

    @Override
    public CreateFlowerResponse createProduct(CreateFlowerRequest request) {
        if (flowerRepository.existsById(request.getId())) {
            throw new IdAlreadyExistsException();
        }

        Flower newProduct = saveNewProduct(request);

        return CreateFlowerResponse.builder()
                .id(newProduct.getId()).build();
    }

    private Flower saveNewProduct(CreateFlowerRequest request) {
        //why not working with parent class
        //Flower newFlower = Flower.builder().name(request.getName()).builder();

        Flower newFlower = Flower.builder().name(request.getName()).price(request.getPrice())
                .description(request.getDescription()).color(request.getColor()).lifeExpectancy(request.getLifeExpectancy())
                .build();
        return flowerRepository.save(newFlower);
    }

    @Override
    public void deleteProduct(Integer productId) {
        this.flowerRepository.deleteById(productId);
    }

    @Override
    public Optional<Flower> getProduct(Integer productId) {
        return Optional.ofNullable(flowerRepository.findById(productId));
    }

    @Override
    public void updateProduct(UpdateFlowerRequest request) {
        Flower product = flowerRepository.findById(request.getId());

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());

        flowerRepository.update(product);

    }
}
