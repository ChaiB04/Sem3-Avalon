package individual.individualsem3backend.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "chat_messages")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "chat_id")
    private Integer chat_id;

    @NotNull
    @Column(name = "sender")
    private Integer sender;

    @NotBlank
    @Column(name = "message")
    private String message;

    @Column(name = "date")
    private Date date;

    @Column(name = "seen")
    private Boolean seen;

}
