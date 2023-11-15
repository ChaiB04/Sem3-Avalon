package individual.individualsem3backend.business.converters;

import individual.individualsem3backend.domain.Product;
import individual.individualsem3backend.persistence.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductEntityConverter {

    private ProductEntityConverter(){

    }

    public static List<Product> listOfProductEntitiesConvertedToListOfProducts(List<ProductEntity> entities){
        return entities.stream().map(ProductEntityConverter::productEntityConvertedToProduct).toList();
    }

    public static Product productEntityConvertedToProduct(ProductEntity entity){
        return Product.builder().id(entity.getId()).name(entity.getName()).color(entity.getColor())
                .price(entity.getPrice()).description(entity.getDescription()).picture(entity.getPicture()).build();
    }

    public static ProductEntity productConvertedToProductEntity(Product product){
        return ProductEntity.builder().id(product.getId()).name(product.getName()).color(product.getColor())
                .price(product.getPrice()).description(product.getDescription()).picture(product.getPicture()).build();
    }

    public static List<ProductEntity> listOfProductConvertedToListOfProductEntities(List<Product> products){
        return products.stream().map(ProductEntityConverter::productConvertedToProductEntity).toList();
    }
}
