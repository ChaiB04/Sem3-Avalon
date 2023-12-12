package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.ChatManager;
import individual.individualsem3backend.controller.converters.ChatAndMessageConverter;
import individual.individualsem3backend.controller.dtos.websocket.ChatLogResponse;
import individual.individualsem3backend.controller.dtos.websocket.ChatMessageRequest;
import individual.individualsem3backend.controller.dtos.websocket.GetAllChats;
import individual.individualsem3backend.domain.Chat;
import individual.individualsem3backend.domain.ChatMessage;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/notifications")
public class ChatController {

    private ChatManager chatManager;
    private ChatAndMessageConverter converter;
    @PostMapping
    public ResponseEntity<ChatMessage> sendMessageToUsers(@RequestBody  @Valid ChatMessageRequest message){
        ChatMessage chatmessage = ChatMessage.builder()
                .chat_id(message.getChat_id())
                .text(message.getText())
                .date(message.getDate())
                .seen(message.getSeen())
                .to(message.getTo())
                .from(message.getFrom())
                .build();

        ChatMessage chatMessage = chatManager.sendMessage(chatmessage);

        if(chatMessage != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(chatMessage);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping()
    public ResponseEntity<GetAllChats> getAllChatsCustomerService(){
        List<Chat> chat = chatManager.getAllChatsCustomerService();
        GetAllChats response = GetAllChats.builder()
                .chats(chat)
                .build();
        System.out.println(response.getChats());

        return ResponseEntity.ok().body(response);
    }

//    @GetMapping("{id}")
//    public ResponseEntity<ChatLogResponse> getChatLogOfCustomerService(@PathVariable Integer id){
////        ChatLogResponse response = ChatLogResponse.builder().getChat(chatManager.getChatMessagesToCustomerService()).build();
//
//        Chat chat = chatManager.getChat(id);
//        ChatLogResponse response = ChatLogResponse.builder()
//                .id(chat.getId())
//                .user_1(chat.getUser_1())
//                .user_2(chat.getUser_2())
//                .chatMessages(chat.getChatMessages().stream().map(converter::chatMessageToMessageDTO).toList())
//                .build();
//
//        return ResponseEntity.ok().body(response);
//    }

}
