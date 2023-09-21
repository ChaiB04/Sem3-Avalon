package individual.individualsem3backend.controller.Converters;

import individual.individualsem3backend.controller.FlowerRequestResponse.*;
import individual.individualsem3backend.domain.Flower;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowerConverter {

    public FlowerConverter(){

    }

    public Flower createFlowerRequestConvertToFlower(CreateFlowerRequest request){
        return Flower.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .color(request.getColor())
                .lifeExpectancy(request.getLifeExpectancy())
                .build();
    }

    public CreateFlowerResponse intConvertToCreateFlowerResponse(Integer id){
        return CreateFlowerResponse.builder().id(id).build();
    }

    public Flower updateFlowerRequestConvertToFlower(UpdateFlowerRequest request){
        return Flower.builder()
                .id(request.getId())
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .color(request.getColor())
                .lifeExpectancy(request.getLifeExpectancy())
                .build();
    }

    public GetAllFlowerResponse flowerListConvertToGetAllFlowerResponse(List<Flower> list){
        return GetAllFlowerResponse.builder().allFlowers(list).build();
    }

    public String getAllFlowerRequestConvertToString(GetAllFlowerRequest request){
        return request.getColor();
    }

}
