package individual.individualsem3backend.domain;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ChatMessage {
    private Integer id;
    private Integer chat_id;
    private Integer from;
    private Integer to;
    private String text;
    private Date date;
    private Boolean seen;
}
