package individual.individualsem3backend.controller.Converters;


import individual.individualsem3backend.controller.DecorationRequestResponse.CreateDecorationRequest;
import individual.individualsem3backend.controller.DecorationRequestResponse.CreateDecorationResponse;
import individual.individualsem3backend.controller.DecorationRequestResponse.GetAllDecorationResponse;
import individual.individualsem3backend.controller.DecorationRequestResponse.UpdateDecorationRequest;
import individual.individualsem3backend.domain.Decoraction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DecorationConverter {

    public DecorationConverter(){

    }

    public Decoraction createDecorationRequestConvertToDecoration(CreateDecorationRequest request){
        return Decoraction.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .category(request.getCategory())
                .build();
    }

    public CreateDecorationResponse intConvertToCreateDecorationResponse(Integer id){
        return CreateDecorationResponse.builder().id(id).build();
    }

    public Decoraction updateDecorationRequestConvertToDecoration(UpdateDecorationRequest request){
        return Decoraction.builder()
                .id(request.getId())
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .category(request.getCategory())
                .build();
    }

    public GetAllDecorationResponse decorationListConvertToGetAllDecorationResponse(List<Decoraction> list){
        return GetAllDecorationResponse.builder().allDecorations(list).build();
    }
}
