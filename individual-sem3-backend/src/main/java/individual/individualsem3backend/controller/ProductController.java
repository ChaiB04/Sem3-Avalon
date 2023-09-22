package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.ProductManagerUseCase;
import individual.individualsem3backend.controller.ProductRequestResponse.GetAllProductRequest;
import individual.individualsem3backend.controller.ProductRequestResponse.GetAllProductsResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductManagerUseCase productManagerUseCase;

    @GetMapping
    public ResponseEntity<GetAllProductsResponse> getAllProducts() {
        GetAllProductRequest request = GetAllProductRequest.builder().build();
        GetAllProductsResponse response = productManagerUseCase.getProducts(request);
        return ResponseEntity.ok(response);
    }
//
//    @PostMapping()
//    public ResponseEntity<CreateProductResponse> createStudent(@RequestBody CreateProductRequest request) {
//        CreateProductResponse response = productManagerUseCase.createProduct(request);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
//
//    @DeleteMapping("{productId}")
//    public ResponseEntity<Void> deleteStudent(@PathVariable Integer productId) {
//        productManagerUseCase.deleteProduct(productId);
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping("{productId}")
//    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
//        final Optional<Product> ProductOptional = productManagerUseCase.getProduct(productId);
//        if (ProductOptional.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok().body(ProductOptional.get());
//    }
//
//    @PutMapping("{productId}")
//    public ResponseEntity<Void> updateProduct(@PathVariable("productId") Integer productId,
//                                              @RequestBody UpdateProductRequest request)
//    {
//        request.setId(productId);
//        productManagerUseCase.updateProduct(request);
//
//        return ResponseEntity.noContent().build();
//    }
}
