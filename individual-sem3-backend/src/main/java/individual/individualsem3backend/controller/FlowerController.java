package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.FlowerManagerUseCase;
import individual.individualsem3backend.controller.Converters.FlowerConverter;
import individual.individualsem3backend.controller.FlowerRequestResponse.*;
import individual.individualsem3backend.domain.Flower;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flowers")
@AllArgsConstructor
public class FlowerController {
    private final FlowerManagerUseCase flowerManagerUseCase;

    private FlowerConverter flowerConverter;

    @GetMapping
    public ResponseEntity<GetAllFlowerResponse> getAllProducts(@RequestParam(value = "color", required = false) String color) {
        GetAllFlowerRequest request = GetAllFlowerRequest.builder().color(color).build();
        String convertRequest = flowerConverter.getAllFlowerRequestConvertToString(request);

        List<Flower> products = flowerManagerUseCase.getProducts(convertRequest);

        GetAllFlowerResponse response = flowerConverter.flowerListConvertToGetAllFlowerResponse(products);

        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<CreateFlowerResponse> createFlower(@RequestBody @Valid CreateFlowerRequest request) {
        Flower convertRequest = flowerConverter.createFlowerRequestConvertToFlower(request);

        Flower product = flowerManagerUseCase.createProduct(convertRequest);

        CreateFlowerResponse response = flowerConverter.intConvertToCreateFlowerResponse(product.getId());
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

        Flower convertRequest = flowerConverter.updateFlowerRequestConvertToFlower(request);

        flowerManagerUseCase.updateProduct(convertRequest);

        return ResponseEntity.noContent().build();
    }


}
