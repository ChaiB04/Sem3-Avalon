package individual.individualsem3backend.controller.dtos.websocket;

import individual.individualsem3backend.domain.Chat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllChats {
    private List<Chat> chats;
}
