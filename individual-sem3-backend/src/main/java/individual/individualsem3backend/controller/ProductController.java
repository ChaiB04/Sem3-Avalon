package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.ProductManagerUseCase;
import individual.individualsem3backend.controller.Converters.ProductConverter;
import individual.individualsem3backend.controller.ProductRequestResponse.*;
import individual.individualsem3backend.domain.Product;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductManagerUseCase productManagerUseCase;
    private ProductConverter converter;

    @GetMapping
    public ResponseEntity<GetAllProductsResponse> getAllProducts() {
        GetAllProductsResponse response = converter.productListConvertToGetAllProductResponse(productManagerUseCase.getProducts());
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody CreateProductRequest request) {

        Product product = converter.createProductRequestConvertToProduct(request);

        CreateProductResponse response = converter.productConvertToCreateProductResponse(productManagerUseCase.createProduct(product));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        productManagerUseCase.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        final Optional<Product> ProductOptional = productManagerUseCase.getProduct(productId);
        if (ProductOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(ProductOptional.get());
    }

    @PutMapping("{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable("productId") Integer productId,
                                              @RequestBody UpdateProductRequest request)
    {
        request.setId(productId);
        productManagerUseCase.updateProduct(converter.updateProductRequestConvertToProduct(request));

        return ResponseEntity.noContent().build();
    }
}
