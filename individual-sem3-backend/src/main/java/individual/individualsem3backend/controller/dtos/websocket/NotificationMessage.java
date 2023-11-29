package individual.individualsem3backend.controller.dtos.websocket;

import individual.individualsem3backend.domain.User;
import lombok.Data;

@Data
public class NotificationMessage {
    private String id;
    private String from;
    private String to;
    private String text;
}
