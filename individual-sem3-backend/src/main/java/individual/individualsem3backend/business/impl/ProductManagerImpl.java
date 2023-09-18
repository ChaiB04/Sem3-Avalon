package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.ProductManagerUseCase;
import individual.individualsem3backend.business.exception.IdAlreadyExistsException;
import individual.individualsem3backend.business.exception.InvalidProductException;
import individual.individualsem3backend.controller.requests.CreateProductRequest;
import individual.individualsem3backend.controller.requests.UpdateProductRequest;
import individual.individualsem3backend.controller.responses.CreateProductResponse;
import individual.individualsem3backend.controller.responses.GetAllProductsResponse;
import individual.individualsem3backend.controller.requests.GetAllProductRequest;
import individual.individualsem3backend.domain.Product;
import individual.individualsem3backend.persistence.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductManagerImpl implements ProductManagerUseCase {
    private ProductRepository productRepository;

    @Override
    public GetAllProductsResponse getProducts(final GetAllProductRequest request) {
        List<Product> results = productRepository.findAll();

        final GetAllProductsResponse response = new GetAllProductsResponse();

        response.setAllProducts(results);

        return response;
    }

    @Override
    public CreateProductResponse createProduct(CreateProductRequest request) {
        if (productRepository.existsById(request.getId())) {
            throw new IdAlreadyExistsException();
        }

        Product newProduct = saveNewProduct(request);

        return CreateProductResponse.builder()
                .productId(newProduct.getId()).build();
    }

    private Product saveNewProduct(CreateProductRequest request) {
        Product newStudent = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .build();
        return productRepository.save(newStudent);
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
    public void updateProduct(UpdateProductRequest request) {
        Product product = productRepository.findById(request.getId());

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());

        productRepository.update(product);

    }
}
