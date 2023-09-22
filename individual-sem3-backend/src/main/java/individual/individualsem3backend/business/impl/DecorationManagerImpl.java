package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.DecorationManagerUseCase;
import individual.individualsem3backend.business.exception.NameAlreadyExistsException;
import individual.individualsem3backend.controller.Converters.DecorationConverter;
import individual.individualsem3backend.controller.DecorationRequestResponse.*;
import individual.individualsem3backend.domain.Decoraction;
import individual.individualsem3backend.persistence.DecorationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class DecorationManagerImpl implements DecorationManagerUseCase {
    private DecorationRepository decorationRepository;

    @Override
    public List<Decoraction> getProducts() {
        return decorationRepository.findAll();
    }

    @Override
    public Decoraction createProduct(Decoraction request) {
        if (decorationRepository.existsByName(request.getName())) {
            throw new NameAlreadyExistsException();
        }

        return decorationRepository.save(request);
    }

//    private Decoraction saveNewProduct(CreateDecorationRequest request) {
//        //why not working with parent class
//        //Flower newFlower = Flower.builder().name(request.getName()).builder();
//
//        Decoraction newDecoration = Decoraction.builder().name(request.getName())
//                .price(request.getPrice()).description(request.getDescription()).warranty(request.getWarranty())
//                .category(request.getCategory()).build();
//
//        return decorationRepository.save(newDecoration);
//    }

    @Override
    public void deleteProduct(Integer productId) {
        this.decorationRepository.deleteById(productId);
    }

    @Override
    public Optional<Decoraction> getProduct(Integer productId) {
        return Optional.ofNullable(decorationRepository.findById(productId));
    }

    @Override
    public void updateProduct(Decoraction request) {
        Decoraction product = decorationRepository.findById(request.getId());

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setCategory(request.getCategory());
        product.setWarranty(request.getWarranty());

        decorationRepository.update(product);

    }
}
