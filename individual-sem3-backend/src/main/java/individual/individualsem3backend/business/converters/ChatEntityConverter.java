package individual.individualsem3backend.business.converters;

import individual.individualsem3backend.controller.dtos.websocket.ChatLogResponse;
import individual.individualsem3backend.controller.dtos.websocket.ChatMessageRequest;
import individual.individualsem3backend.domain.Chat;
import individual.individualsem3backend.domain.ChatMessage;
import individual.individualsem3backend.domain.Product;
import individual.individualsem3backend.persistence.entity.ChatEntity;
import individual.individualsem3backend.persistence.entity.MessageEntity;
import individual.individualsem3backend.persistence.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatEntityConverter {

//    public List<ChatMessage> listOfChatEntitiesConvertedToListOfChatMessages(List<ChatEntity> entities){
//        return entities.stream().map(this::c).toList();
//    }
    public ChatMessage chatMessageEntityToChatMessage(MessageEntity dto){
        return ChatMessage.builder()
                .id(dto.getId())
                .chat_id(dto.getChat_id())
                .from(dto.getSender())
                .text(dto.getMessage())
                .date(dto.getDate())
                .seen(dto.getSeen())
                .build();
    }

    public MessageEntity chatMessageToChatMessageEntity(ChatMessage chatMessage){
        return MessageEntity.builder()
                .id(chatMessage.getId())
                .chat_id(chatMessage.getChat_id())
                .sender(chatMessage.getFrom())
                .message(chatMessage.getText())
                .date(chatMessage.getDate())
                .seen(chatMessage.getSeen())
                .build();
    }


    public Chat chatEntityToChat(ChatEntity dto){
        return Chat.builder()
                .id(dto.getId())
                .user_1(dto.getUser1())
                .user_2(dto.getUser2())
                .chatMessages(dto.getMessages().stream().map(this::chatMessageEntityToChatMessage).toList())
                .build();
    }

    public ChatEntity chatConvertedToChatEntity(Chat chatMessage){
        return ChatEntity.builder().id(chatMessage.getId())
                .user1(chatMessage.getUser_1())
                .user2(chatMessage.getUser_2())
                .messages(chatMessage.getChatMessages().stream().map(this::chatMessageToChatMessageEntity).toList())
                .build();
    }



//    public ChatMessage chatMessageDtoToChatMessage(ChatMessageRequest dto){
//        return ChatMessage.builder()
//                .id(dto.getId())
//                .from(dto.getFrom())
//                .text(dto.getText())
//                .date(dto.getDate())
//                .seen(dto.getSeen())
//                .build();
//    }
//
//    public ChatMessageRequest chatMessageToChatMessageDTO(ChatMessage chatMessage){
//        return ChatMessageRequest.builder()
//                .id(chatMessage.getId())
//                .from(chatMessage.getFrom())
//                .text(chatMessage.getText())
//                .date(chatMessage.getDate())
//                .seen(chatMessage.getSeen())
//                .build();
//    }
//
//
//    public Chat chatDtoToChat(ChatLogResponse dto){
//        return Chat.builder()
//                .id(dto.getId())
//                .user_1(dto.getUser_1())
//                .user_2(dto.getUser_2())
//                .chatMessages()
//                .build();
//    }


}
