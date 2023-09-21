package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.DecorationManagerUseCase;
import individual.individualsem3backend.controller.Converters.DecorationConverter;
import individual.individualsem3backend.controller.DecorationRequestResponse.*;
import individual.individualsem3backend.domain.Decoraction;
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

    private DecorationConverter decorationConverter;

    @GetMapping
    public ResponseEntity<GetAllDecorationResponse> getAllProducts() {
       //Filtering
        // GetAllDecorationRequest request = GetAllDecorationRequest.builder().build();

        GetAllDecorationResponse response = decorationConverter.decorationListConvertToGetAllDecorationResponse(decorationManagerUseCase.getProducts());
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<CreateDecorationResponse> createStudent(@RequestBody CreateDecorationRequest request) {
        Decoraction convertRequest = decorationConverter.createDecorationRequestConvertToDecoration(request);

        Decoraction product = decorationManagerUseCase.createProduct(convertRequest);

        CreateDecorationResponse response = decorationConverter.intConvertToCreateDecorationResponse(product.getId());
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

        Decoraction convertRequest = decorationConverter.updateDecorationRequestConvertToDecoration(request);
        decorationManagerUseCase.updateProduct(convertRequest);

        return ResponseEntity.noContent().build();
    }
}
