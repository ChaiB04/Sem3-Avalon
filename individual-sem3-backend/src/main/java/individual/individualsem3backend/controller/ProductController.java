package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.ProductManagerUseCase;
import individual.individualsem3backend.business.*;
import individual.individualsem3backend.controller.requests.CreateProductRequest;
import individual.individualsem3backend.controller.responses.CreateProductResponse;
import individual.individualsem3backend.domain.*;
import individual.individualsem3backend.controller.requests.GetAllProductRequest;
import individual.individualsem3backend.controller.responses.GetAllProductsResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping()
    public ResponseEntity<CreateProductResponse> createStudent(@RequestBody CreateProductRequest request) {
        CreateProductResponse response = productManagerUseCase.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
