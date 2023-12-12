package individual.individualsem3backend.controller.dtos.websocket;

import individual.individualsem3backend.domain.ChatMessage;
import lombok.*;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatLogResponse {
    private Integer id;
    private Integer user_1;
    private Integer user_2;
    private List<ChatMessageRequest> chatMessages;
}
