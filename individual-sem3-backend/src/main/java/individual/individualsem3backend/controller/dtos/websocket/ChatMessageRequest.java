package individual.individualsem3backend.controller.dtos.websocket;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageRequest {
    private String id;
    private Integer chat_id;
    private Integer from;
    private Integer to;
    private String text;
    private Date date;
    private Boolean seen;
}
