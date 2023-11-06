package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.ProductManager;
import individual.individualsem3backend.controller.converters.ProductConverter;
import individual.individualsem3backend.controller.dtos.product.*;
import individual.individualsem3backend.domain.Product;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductManager productManagerUseCase;
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
        Product product = productManagerUseCase.getProduct(productId);
        return ResponseEntity.ok().body(product);
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
