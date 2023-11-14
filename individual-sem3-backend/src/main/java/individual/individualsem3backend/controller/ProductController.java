package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.ProductManager;
import individual.individualsem3backend.controller.converters.ProductConverter;
import individual.individualsem3backend.controller.dtos.product.*;
import individual.individualsem3backend.domain.Product;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductManager productManager;
    private ProductConverter converter;

    @RolesAllowed({"CUSTOMER", "ADMINISTATOR"})
    @GetMapping
    public ResponseEntity<GetAllProductsResponse> getAllProducts() {
        GetAllProductsResponse response = converter.productListConvertToGetAllProductResponse(productManager.getProducts());
        return ResponseEntity.ok(response);
    }

    @RolesAllowed({"CUSTOMER", "ADMINISTRATOR"})
    @PostMapping()
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody CreateProductRequest request) {

        Product product = converter.createProductRequestConvertToProduct(request);

        CreateProductResponse response = converter.productConvertToCreateProductResponse(productManager.createProduct(product));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @RolesAllowed({"ADMINISTRATOR"})
    @DeleteMapping("{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        productManager.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

//    @RolesAllowed({"CUSTOMER", "ADMINISTRATOR"})
    @GetMapping("{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        Product product = productManager.getProduct(productId);
        return ResponseEntity.ok().body(product);
    }

    @RolesAllowed({"ADMINISTRATOR"})
    @PutMapping("{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable("productId") Integer productId,
                                              @RequestBody UpdateProductRequest request)
    {
        request.setId(productId);
        productManager.updateProduct(converter.updateProductRequestConvertToProduct(request));

        return ResponseEntity.noContent().build();
    }
}
