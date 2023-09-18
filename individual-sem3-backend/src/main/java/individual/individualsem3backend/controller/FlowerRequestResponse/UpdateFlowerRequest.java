package individual.individualsem3backend.controller.FlowerRequestResponse;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFlowerRequest {
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private Double price;
    private String description;
    private String color;
    private int lifeExpectancy;
}
