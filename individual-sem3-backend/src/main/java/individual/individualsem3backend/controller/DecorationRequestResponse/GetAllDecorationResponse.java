package individual.individualsem3backend.controller.DecorationRequestResponse;

import individual.individualsem3backend.domain.Decoraction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllDecorationResponse {
    private List<Decoraction> allDecorations;
}
