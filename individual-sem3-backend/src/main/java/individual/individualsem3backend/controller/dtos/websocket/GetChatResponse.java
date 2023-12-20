package individual.individualsem3backend.controller.dtos.websocket;

import individual.individualsem3backend.domain.Chat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetChatResponse {
    private Chat chat;
}
