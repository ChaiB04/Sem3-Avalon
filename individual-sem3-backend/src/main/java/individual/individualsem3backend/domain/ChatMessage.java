package individual.individualsem3backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private Integer id;
    private Integer chat_id;
    private Integer from;
    private Integer to;
    private String text;
    private Date date;
    private Boolean seen;
}
