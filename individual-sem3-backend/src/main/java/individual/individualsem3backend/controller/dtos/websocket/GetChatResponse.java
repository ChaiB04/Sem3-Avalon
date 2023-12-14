package individual.individualsem3backend.controller.dtos.websocket;

import individual.individualsem3backend.domain.Chat;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetChatResponse {
    private Chat chat;
}
