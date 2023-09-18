package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.DecorationManagerUseCase;
import individual.individualsem3backend.business.FlowerManagerUseCase;
import individual.individualsem3backend.controller.DecorationRequestResponse.*;
import individual.individualsem3backend.controller.FlowerRequestResponse.*;
import individual.individualsem3backend.controller.responses.CreateProductResponse;
import individual.individualsem3backend.domain.Decoraction;
import individual.individualsem3backend.domain.Flower;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/decorations")
@AllArgsConstructor
public class DecorationController {
    private final DecorationManagerUseCase decorationManagerUseCase;

    @GetMapping
    public ResponseEntity<GetAllDecorationResponse> getAllProducts() {
        GetAllDecorationRequest request = GetAllDecorationRequest.builder().build();
        GetAllDecorationResponse response = decorationManagerUseCase.getProducts(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<CreateDecorationResponse> createStudent(@RequestBody CreateDecorationRequest request) {
        CreateDecorationResponse response = decorationManagerUseCase.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer productId) {
        decorationManagerUseCase.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{productId}")
    public ResponseEntity<Decoraction> getProduct(@PathVariable Integer productId){
        final Optional<Decoraction> ProductOptional = decorationManagerUseCase.getProduct(productId);
        if (ProductOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(ProductOptional.get());
    }

    @PutMapping("{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable("productId") Integer productId,
                                              @RequestBody UpdateDecorationRequest request)
    {
        request.setId(productId);
        decorationManagerUseCase.updateProduct(request);

        return ResponseEntity.noContent().build();
    }
}
