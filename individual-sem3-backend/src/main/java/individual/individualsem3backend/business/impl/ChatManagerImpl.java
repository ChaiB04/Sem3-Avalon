package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.ChatManager;
import individual.individualsem3backend.business.converters.ChatEntityConverter;
import individual.individualsem3backend.business.exception.WebSocketException;
import individual.individualsem3backend.domain.Chat;
import individual.individualsem3backend.domain.ChatMessage;
import individual.individualsem3backend.persistence.ChatRepository;
import individual.individualsem3backend.persistence.MessageRepository;
import individual.individualsem3backend.persistence.entity.ChatEntity;
import individual.individualsem3backend.persistence.entity.MessageEntity;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChatManagerImpl implements ChatManager {
    private final SimpMessagingTemplate messagingTemplate;
    private ChatRepository chatRepository;
    private ChatEntityConverter converter;
    private MessageRepository messageRepository;

    public ChatMessage sendMessage(ChatMessage message){
        messagingTemplate.convertAndSend("/topic/publicmessages", message);

        message.setSeen(false);
        if(message.getChat_id() != null){
            if(chatRepository.existsById(message.getChat_id())){
                MessageEntity response = messageRepository.save(converter.chatMessageToChatMessageEntity(message));

                if(response != null && response.getId() != null){
                    return converter.chatMessageEntityToChatMessage(response);
                }
            }
            else{
                throw new WebSocketException("Chat not found.");
            }
        }
        else{
            // Create a new chat
            Chat newChat = Chat.builder()
                    .user_1(message.getFrom())
                    .user_2(message.getTo())
                    .chatMessages(new ArrayList<>())
                    .build();


            ChatEntity id = chatRepository.saveAndFlush(converter.chatConvertedToChatEntity(newChat));

            message.setChat_id(id.getId());

            MessageEntity response = messageRepository.save(converter.chatMessageToChatMessageEntity(message));

            if (response != null && response.getId() != null) {
                return converter.chatMessageEntityToChatMessage(response);
            } else {
                throw new WebSocketException("Error saving message.");
            }

        }
        return null;
    }

    public Chat getChat(Integer id){
        Optional<ChatEntity> chat = chatRepository.findById(id);

        if(chat.isPresent()){
            return converter.chatEntityToChat(chat.get());
        }
        else{
            throw null;
        }
    }

    public List<Chat> getAllChatsCustomerService(){
        List<ChatEntity> chat1 = chatRepository.findAllByUser1(2);
        List<ChatEntity> chat2 = chatRepository.findAllByUser2(2);

        List<ChatEntity> combinedList = new ArrayList<>();
        combinedList.addAll(chat1);
        combinedList.addAll(chat2);

        return combinedList.stream().map(converter::chatEntityToChat).toList();
    }
}
