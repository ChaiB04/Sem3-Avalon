package individual.individualsem3backend.controller.Converters;

import individual.individualsem3backend.controller.BouquetRequestResponse.CreateBouquetRequest;
import individual.individualsem3backend.controller.BouquetRequestResponse.CreateBouquetResponse;
import individual.individualsem3backend.controller.BouquetRequestResponse.GetAllBouquetResponse;
import individual.individualsem3backend.controller.BouquetRequestResponse.UpdateBouquetRequest;
import individual.individualsem3backend.domain.Bouquet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BouquetConverter {
    public BouquetConverter(){

    }

    public Bouquet createBouquetRequestConvertToBouquet(CreateBouquetRequest request){
        return Bouquet.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .colorOfBow(request.getColorOfBow())
                .flowers(request.getFlowers())
                .build();
    }

    public CreateBouquetResponse intConvertToCreateBouquetResponse(Integer id){
        return CreateBouquetResponse.builder().id(id).build();
    }

    public Bouquet updateBouquetRequestConvertToBouquet(UpdateBouquetRequest request){
        return Bouquet.builder()
                .id(request.getId())
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .colorOfBow(request.getColorOfBow())
                .flowers(request.getFlowers())
                .build();
    }

    public GetAllBouquetResponse bouquetListConvertToGetAllBouquetResponse(List<Bouquet> list){
        return GetAllBouquetResponse.builder().allBouquets(list).build();
    }
}
