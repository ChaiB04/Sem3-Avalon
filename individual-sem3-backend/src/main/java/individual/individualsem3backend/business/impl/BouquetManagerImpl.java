package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.BouquetManagerUseCase;
import individual.individualsem3backend.business.exception.NameAlreadyExistsException;
import individual.individualsem3backend.controller.BouquetRequestResponse.*;
import individual.individualsem3backend.domain.Bouquet;
import individual.individualsem3backend.persistence.BouquetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BouquetManagerImpl implements BouquetManagerUseCase {
    private BouquetRepository bouquetRepository;

    @Override
    public List<Bouquet> getProducts() {
        return bouquetRepository.findAll();
    }

    @Override
    public Bouquet createProduct(Bouquet request) {
        if (bouquetRepository.existsByName(request.getName())) {
            throw new NameAlreadyExistsException();
        }

        return bouquetRepository.save(request);
    }

//    private Bouquet saveNewProduct(CreateBouquetRequest request) {
//        Bouquet newDecoration = Bouquet.builder().name(request.getName())
//                .price(request.getPrice()).description(request.getDescription()).colorOfBow(request.getColorOfBow())
//                .flowers(request.getFlowers()).build();
//
//        return bouquetRepository.save(newDecoration);
//    }

    @Override
    public void deleteProduct(Integer productId) {
        this.bouquetRepository.deleteById(productId);
    }

    @Override
    public Optional<Bouquet> getProduct(Integer productId) {
        return Optional.ofNullable(bouquetRepository.findById(productId));
    }

    @Override
    public void updateProduct(Bouquet request) {
        Bouquet product = bouquetRepository.findById(request.getId());

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setColorOfBow(request.getColorOfBow());
        product.setFlowers(request.getFlowers());

        bouquetRepository.update(product);

    }
}
