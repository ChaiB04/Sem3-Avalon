package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.BouquetManagerUseCase;
import individual.individualsem3backend.controller.BouquetRequestResponse.*;
import individual.individualsem3backend.domain.Bouquet;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/bouquets")
@AllArgsConstructor
public class BouquetController {
    private final BouquetManagerUseCase bouquetManagerUseCase;

    @GetMapping
    public ResponseEntity<GetAllBouquetResponse> getAllProducts() {
        GetAllBouquetRequest request = GetAllBouquetRequest.builder().build();
        GetAllBouquetResponse response = bouquetManagerUseCase.getProducts(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<CreateBouquetResponse> createStudent(@RequestBody CreateBouquetRequest request) {
        CreateBouquetResponse response = bouquetManagerUseCase.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer productId) {
        bouquetManagerUseCase.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{productId}")
    public ResponseEntity<Bouquet> getProduct(@PathVariable Integer productId){
        final Optional<Bouquet> ProductOptional = bouquetManagerUseCase.getProduct(productId);
        if (ProductOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(ProductOptional.get());
    }

    @PutMapping("{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable("productId") Integer productId,
                                              @RequestBody UpdateBouquetRequest request)
    {
        request.setId(productId);
        bouquetManagerUseCase.updateProduct(request);

        return ResponseEntity.noContent().build();
    }
}
