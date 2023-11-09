package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.ProductManager;
import individual.individualsem3backend.business.converters.ProductEntityConverter;
import individual.individualsem3backend.business.exception.ProductException;
import individual.individualsem3backend.domain.Product;
import individual.individualsem3backend.persistence.ProductRepository;
import individual.individualsem3backend.persistence.entity.ProductEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductManagerImpl implements ProductManager {

    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        try{
            return ProductEntityConverter.listOfProductEntitiesConvertedToListOfProducts(productRepository.findAll());
        }
        catch(Exception ex){
            throw new ProductException("Something went wrong with getting all the products.");
        }
    }

    @Override
    public Product createProduct(Product request) {
        try{
            if(request != null){
                Product newProduct = Product.builder()
                        .name(request.getName())
                        .price(request.getPrice())
                        .description(request.getDescription())
                        .color(request.getColor())
                        .build();

                return ProductEntityConverter.productEntityConvertedToProduct(productRepository.save(ProductEntityConverter.productConvertedToProductEntity(newProduct)));
            }
            else{
                throw new ProductException("Cannot receive information to create product.");
            }
        }
        catch(Exception ex){
            throw new ProductException(ex.getMessage());
        }
    }


    @Override
    public void deleteProduct(int productId) {
        try{
                this.productRepository.deleteById(productId);
        }
        catch(Exception ex){
            throw new ProductException(ex.getMessage());
        }
    }

    @Override
    public Product getProduct(int productId) {
        try {
                Optional<ProductEntity> optionalProductEntity = productRepository.findById(productId);

                if (optionalProductEntity.isPresent()) {
                    return ProductEntityConverter.productEntityConvertedToProduct(optionalProductEntity.get());
                } else {
                    throw new ProductException("Product not found for ID: " + productId);
                }
        } catch (ProductException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ProductException("Something went wrong while finding the product.");
        }
    }

    @Override
    public void updateProduct(Product request) {
        try {
            if (request != null) {
                Optional<ProductEntity> optionalProductEntity = productRepository.findById(request.getId());

                if (optionalProductEntity.isPresent()) {
                    ProductEntity productEntity = optionalProductEntity.get();
                    Product product = ProductEntityConverter.productEntityConvertedToProduct(productEntity);

                    product.setName(request.getName());
                    product.setPrice(request.getPrice());
                    product.setColor(request.getColor());
                    product.setDescription(request.getDescription());

                    productRepository.save(ProductEntityConverter.productConvertedToProductEntity(product));
                } else {
                    throw new ProductException("Product not found for ID: " + request.getId());
                }
            } else {
                throw new ProductException("Invalid product information provided for update.");
            }
        } catch (ProductException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ProductException("Something went wrong while updating the product.");
        }
    }



}
