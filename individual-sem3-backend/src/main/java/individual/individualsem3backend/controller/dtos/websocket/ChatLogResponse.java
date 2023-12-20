package individual.individualsem3backend.controller.dtos.websocket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
