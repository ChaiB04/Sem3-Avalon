package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.ProductManager;
import individual.individualsem3backend.controller.converters.ProductConverter;
import individual.individualsem3backend.controller.dtos.product.*;
import individual.individualsem3backend.domain.Product;
import individual.individualsem3backend.domain.ProductFilter;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductManager productManager;
    private ProductConverter converter;

    //@RolesAllowed({"CUSTOMER", "ADMINISTRATOR"})
    @GetMapping
    public ResponseEntity<GetAllProductsResponse> getAllProducts(@ModelAttribute GetAllProductRequest request) {
        ProductFilter filter = converter.GetAllProductsFilterToProductFilter(request);
        GetAllProductsResponse response = converter.productListConvertToGetAllProductResponse(productManager.getProducts(filter));
        return ResponseEntity.ok(response);
    }

    //@RolesAllowed({"ADMINISTRATOR"})
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

   // @RolesAllowed({"ADMINISTRATOR", "CUSTOMER"})
    @GetMapping("{productId}")
    public ResponseEntity<GetProductResponse> getProduct(@PathVariable Integer productId){
        GetProductResponse response = converter.productToGetProductResponse(productManager.getProduct(productId));
        return ResponseEntity.ok().body(response);
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
