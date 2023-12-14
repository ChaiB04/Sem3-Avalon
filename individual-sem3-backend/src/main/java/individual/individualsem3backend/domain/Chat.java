package individual.individualsem3backend.domain;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    private Integer id;
    private Integer user_1;
    private Integer user_2;
    private List<ChatMessage> chatMessages;
}
