package individual.individualsem3backend.controller.Converters;

import individual.individualsem3backend.controller.ProductRequestResponse.*;
import individual.individualsem3backend.domain.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductConverter {

    public Product createProductRequestConvertToProduct(CreateProductRequest request){
        return Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .color(request.getColor()).build();
    }

    public CreateProductResponse productConvertToCreateProductResponse(Product product){
        return CreateProductResponse.builder()
                .productId(product.getId()).build();
    }

    public Product updateProductRequestConvertToProduct(UpdateProductRequest request){
        return Product.builder()
                .id(request.getId())
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .color(request.getColor()).build();
    }

    public GetAllProductsResponse productListConvertToGetAllProductResponse(List<Product> productList){
        return GetAllProductsResponse.builder().allProducts(productList).build();
    }

}
