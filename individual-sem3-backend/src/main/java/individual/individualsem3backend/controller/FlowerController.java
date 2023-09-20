package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.FlowerManagerUseCase;
import individual.individualsem3backend.controller.FlowerRequestResponse.*;
import individual.individualsem3backend.domain.Flower;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/flowers")
@AllArgsConstructor
public class FlowerController {
    private final FlowerManagerUseCase flowerManagerUseCase;

    @GetMapping
    public ResponseEntity<GetAllFlowerResponse> getAllProducts() {
        GetAllFlowerRequest request = GetAllFlowerRequest.builder().build();
        GetAllFlowerResponse response = flowerManagerUseCase.getProducts(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<CreateFlowerResponse> createStudent(@RequestBody @Valid CreateFlowerRequest request) {
        CreateFlowerResponse response = flowerManagerUseCase.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer productId) {
        flowerManagerUseCase.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{productId}")
    public ResponseEntity<Flower> getProduct(@PathVariable Integer productId){
        final Optional<Flower> ProductOptional = flowerManagerUseCase.getProduct(productId);
        if (ProductOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(ProductOptional.get());
    }

    @PutMapping("{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable("productId")  Integer productId,
                                              @RequestBody @Valid UpdateFlowerRequest request)
    {
        request.setId(productId);
        flowerManagerUseCase.updateProduct(request);

        return ResponseEntity.noContent().build();
    }


}
