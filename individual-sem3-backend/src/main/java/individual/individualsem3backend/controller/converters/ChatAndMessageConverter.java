package individual.individualsem3backend.controller.converters;

import individual.individualsem3backend.controller.dtos.websocket.ChatMessageRequest;
import individual.individualsem3backend.domain.ChatMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChatAndMessageConverter {
    public ChatMessageRequest chatMessageToMessageDTO(ChatMessage message){
        return ChatMessageRequest.builder()
                .from(message.getFrom())
                .chat_id(message.getChat_id())
                .text(message.getText())
                .date(message.getDate())
                .seen(message.getSeen())
                .build();
    }

    public ChatMessage chatMessageDTOToMessage(ChatMessageRequest dto){
        return ChatMessage.builder()
                .chat_id(dto.getChat_id())
                .from(dto.getFrom())
                .text(dto.getText())
                .date(dto.getDate())
                .seen(dto.getSeen())
                .build();
    }
}
