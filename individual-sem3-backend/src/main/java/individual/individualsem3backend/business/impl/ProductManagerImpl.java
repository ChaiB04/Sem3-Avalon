package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.ProductManagerUseCase;
import individual.individualsem3backend.domain.Product;
import individual.individualsem3backend.persistence.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductManagerImpl implements ProductManagerUseCase {

    private ProductRepository productRepository;

    @Override
    public List<Product>  getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product request) {

        Product newProduct = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .build();

        return productRepository.save(newProduct);
    }


    @Override
    public void deleteProduct(int productId) {

        this.productRepository.deleteById(productId);
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return Optional.ofNullable(productRepository.findById(productId));
    }

    @Override
    public void updateProduct(Product request) {
        Product product = productRepository.findById(request.getId());

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());

        productRepository.update(product);

    }


}
