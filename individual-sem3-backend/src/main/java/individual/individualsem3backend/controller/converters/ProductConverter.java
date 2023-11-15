package individual.individualsem3backend.controller.converters;

import individual.individualsem3backend.controller.dtos.product.*;
import individual.individualsem3backend.domain.Product;
import individual.individualsem3backend.domain.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductConverter {

    public Product createProductRequestConvertToProduct(CreateProductRequest request){
        return Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .color(request.getColor())
                .picture(request.getPicture())
                .build();
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
                .picture(request.getPicture())
                .color(request.getColor()).build();
    }

    public GetProductResponse productToGetProductResponse(Product product){
        String base64EncodedString = Base64.getEncoder().encodeToString(product.getPicture());
        return GetProductResponse.builder()
                .id(product.getId())
                .color(product.getColor())
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .base64picture(base64EncodedString)
                .build();

    }

    public ProductFilter GetAllProductsFilterToProductFilter(GetAllProductRequest request){
        return ProductFilter.builder().name(request.getName()).price(request.getPrice()).color(request.getColor()).build();
    }

    public GetAllProductsResponse productListConvertToGetAllProductResponse(List<Product> productList){
        return GetAllProductsResponse.builder().allProducts(productList).build();
    }

}
