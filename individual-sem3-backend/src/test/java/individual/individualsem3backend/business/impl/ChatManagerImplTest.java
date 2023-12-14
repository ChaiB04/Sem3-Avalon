package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.exception.WebSocketException;
import individual.individualsem3backend.domain.Chat;
import individual.individualsem3backend.domain.ChatMessage;
import individual.individualsem3backend.persistence.ChatRepository;
import individual.individualsem3backend.persistence.MessageRepository;
import individual.individualsem3backend.persistence.entity.ChatEntity;
import individual.individualsem3backend.persistence.entity.MessageEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChatManagerImplTest {

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @Mock
    private ChatRepository chatRepository;


    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private ChatManagerImpl chatManager;

    @Test
     void testSendMessageWithExistingChat() {
        ChatMessage message = ChatMessage.builder()
                .chat_id(1)
                .to(1)
                .text("weeeee")
                .seen(false)
                .from(2)
                .date(Date.valueOf("2023-01-01"))
                .build();

        MessageEntity messageEntity = MessageEntity.builder()
                .chat_id(1)

                .message("weeeee")
                .seen(false)
                .sender(2)
                .date(Date.valueOf("2023-01-01"))
                .build();

        MessageEntity messageEntityresult = MessageEntity.builder()
                .id(1)
                .chat_id(1)
                .message("weeeee")
                .seen(false)
                .sender(2)
                .date(Date.valueOf("2023-01-01"))
                .build();


        ChatMessage messageresult = ChatMessage.builder()
                .id(1)
                .chat_id(1)
                .to(null)
                .text("weeeee")
                .seen(false)
                .from(2)
                .date(Date.valueOf("2023-01-01"))
                .build();


        doNothing().when(messagingTemplate).convertAndSend(anyString(), eq(message));
        when(chatRepository.existsById(1)).thenReturn(true);

        when(messageRepository.save(eq(messageEntity))).thenReturn(messageEntityresult);

        ChatMessage result = chatManager.sendMessage(message);
        verify(messagingTemplate).convertAndSend(anyString(), eq(message));
        verify(chatRepository).existsById(1);
        verify(messageRepository).save(eq(messageEntity));

        assertEquals(messageresult, result);
    }

    @Test
     void testSendMessageThrowsException_CannotCreateMessage() {
        ChatMessage message = ChatMessage.builder()
                .chat_id(1)
                .to(1)
                .text("weeeee")
                .seen(false)
                .from(2)
                .date(Date.valueOf("2023-01-01"))
                .build();

        MessageEntity messageEntity = MessageEntity.builder()
                .chat_id(1)

                .message("weeeee")
                .seen(false)
                .sender(2)
                .date(Date.valueOf("2023-01-01"))
                .build();


        doNothing().when(messagingTemplate).convertAndSend(anyString(), eq(message));
        when(chatRepository.existsById(1)).thenReturn(true);

        when(messageRepository.save(eq(messageEntity))).thenReturn(null);

        assertThrows(WebSocketException.class, () -> chatManager.sendMessage(message));
        verify(messagingTemplate).convertAndSend(anyString(), eq(message));
        verify(chatRepository).existsById(1);
    }


    @Test
     void testSendMessageThrowsException_ChatNotFound() {
        ChatMessage message = ChatMessage.builder()
                .chat_id(1)
                .to(1)
                .text("weeeee")
                .seen(false)
                .from(2)
                .date(Date.valueOf("2023-01-01"))
                .build();


        doNothing().when(messagingTemplate).convertAndSend(anyString(), eq(message));
        when(chatRepository.existsById(1)).thenReturn(false);

        assertThrows(WebSocketException.class, () -> chatManager.sendMessage(message));
        verify(messagingTemplate).convertAndSend(anyString(), eq(message));
        verify(chatRepository).existsById(1);
    }

    @Test
     void testSendMessageWithoutAnExistingChat() {
        ChatMessage message = ChatMessage.builder()
                .chat_id(null)
                .to(1)
                .text("weeeee")
                .seen(false)
                .from(2)
                .date(Date.valueOf("2023-01-01"))
                .build();

        MessageEntity messageEntity = MessageEntity.builder()
                .chat_id(1)
                .message("weeeee")
                .seen(false)
                .sender(2)
                .date(Date.valueOf("2023-01-01"))
                .build();

        MessageEntity messageEntityresult = MessageEntity.builder()
                .id(1)
                .chat_id(1)
                .message("weeeee")
                .seen(false)
                .sender(2)
                .date(Date.valueOf("2023-01-01"))
                .build();


        ChatMessage messageresult = ChatMessage.builder()
                .id(1)
                .chat_id(1)
                .to(null)
                .text("weeeee")
                .seen(false)
                .from(2)
                .date(Date.valueOf("2023-01-01"))
                .build();

        ChatEntity newChat = ChatEntity.builder()
                .user1(2)
                .user2(1)
                .messages(new ArrayList<>())
                .build();

        ChatEntity newChatResult = ChatEntity.builder()
                .id(1)
                .user1(2)
                .user2(1)
                .messages(new ArrayList<>())
                .build();


        doNothing().when(messagingTemplate).convertAndSend(anyString(), eq(message));
        when(chatRepository.saveAndFlush(eq(newChat))).thenReturn(newChatResult);

        when(messageRepository.save(eq(messageEntity))).thenReturn(messageEntityresult);

        ChatMessage result = chatManager.sendMessage(message);
        verify(messagingTemplate).convertAndSend(anyString(), eq(message));
        verify(messageRepository).save(eq(messageEntity));
        verify(chatRepository).saveAndFlush(eq(newChat));

        assertEquals(messageresult, result);
    }

    @Test
     void testSendMessageWithoutAnExistingChat_ThrowsException() {
        ChatMessage message = ChatMessage.builder()
                .chat_id(null)
                .to(1)
                .text("weeeee")
                .seen(false)
                .from(2)
                .date(Date.valueOf("2023-01-01"))
                .build();

        MessageEntity messageEntity = MessageEntity.builder()
                .chat_id(1)
                .message("weeeee")
                .seen(false)
                .sender(2)
                .date(Date.valueOf("2023-01-01"))
                .build();

        ChatEntity newChat = ChatEntity.builder()
                .user1(2)
                .user2(1)
                .messages(new ArrayList<>())
                .build();

        ChatEntity newChatResult = ChatEntity.builder()
                .id(1)
                .user1(2)
                .user2(1)
                .messages(new ArrayList<>())
                .build();


        doNothing().when(messagingTemplate).convertAndSend(anyString(), eq(message));
        when(chatRepository.saveAndFlush(eq(newChat))).thenReturn(newChatResult);

        when(messageRepository.save(eq(messageEntity))).thenReturn(null);

//        ChatMessage result = chatManager.sendMessage(message);
        assertThrows(WebSocketException.class, () -> chatManager.sendMessage(message));
        verify(messagingTemplate).convertAndSend(anyString(), eq(message));
        verify(messageRepository).save(eq(messageEntity));
        verify(chatRepository).saveAndFlush(eq(newChat));

//        assertEquals(messageresult, result);
    }

    @Test
    void getChatSuccessfully(){
        ChatEntity chatEntity = ChatEntity
                .builder()
                .id(1)
                .user1(1)
                .user2(2)
                .messages(new ArrayList<>())
                .build();

        when(chatRepository.findById(1)).thenReturn(Optional.ofNullable(chatEntity));

        Chat result = chatManager.getChat(1);
        Chat expected = Chat.builder()
                        .id(1)
                        .user_1(1)
                        .user_2(2)
                        .chatMessages(new ArrayList<>())
                        .build();

        verify(chatRepository).findById(1);

        assertEquals(expected, result);
    }

    @Test
    void getChat_ThrowsException_WhenChatIsNull(){
        when(chatRepository.findById(1)).thenReturn(null);

        assertThrows(WebSocketException.class, () -> chatManager.getChat(1));
        verify(chatRepository).findById(1);
    }

    @Test
    void getChat_ThrowsException_WhenChatIsEmpty() {
        when(chatRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(WebSocketException.class, () -> chatManager.getChat(1));
        verify(chatRepository).findById(1);
    }

}
