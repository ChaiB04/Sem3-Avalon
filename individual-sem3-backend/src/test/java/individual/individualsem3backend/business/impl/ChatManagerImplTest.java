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
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChatManagerImplTest {

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

        when(messageRepository.save(messageEntity)).thenReturn(messageEntityresult);

        ChatMessage result = chatManager.sendMessage(message);
        verify(messagingTemplate).convertAndSend(anyString(), eq(message));
        verify(chatRepository).existsById(1);
        verify(messageRepository).save(messageEntity);

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

        when(messageRepository.save(messageEntity)).thenReturn(null);

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
        when(chatRepository.saveAndFlush(newChat)).thenReturn(newChatResult);

        when(messageRepository.save(messageEntity)).thenReturn(messageEntityresult);

        ChatMessage result = chatManager.sendMessage(message);
        verify(messagingTemplate).convertAndSend(anyString(), eq(message));
        verify(messageRepository).save(messageEntity);
        verify(chatRepository).saveAndFlush(newChat);

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
        when(chatRepository.saveAndFlush(newChat)).thenReturn(newChatResult);

        when(messageRepository.save(messageEntity)).thenReturn(null);

//        ChatMessage result = chatManager.sendMessage(message);
        assertThrows(WebSocketException.class, () -> chatManager.sendMessage(message));
        verify(messagingTemplate).convertAndSend(anyString(), eq(message));
        verify(messageRepository).save(messageEntity);
        verify(chatRepository).saveAndFlush(newChat);

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

    @Test
    void getAllChatsOfCustomerService_Successfully() {
        Chat chat1 = Chat.builder()
                .id(1)
                .user_1(2)
                .user_2(1)
                .chatMessages(new ArrayList<>())
                .build();

        Chat chat2 = Chat.builder()
                .id(2)
                .user_1(2)
                .user_2(5)
                .chatMessages(new ArrayList<>())
                .build();

        ChatEntity chatEntity1 = ChatEntity.builder()
                .id(1)
                .user1(2)
                .user2(1)
                .messages(new ArrayList<>())
                .build();

        ChatEntity chatEntity2 = ChatEntity.builder()
                .id(2)
                .user1(2)
                .user2(5)
                .messages(new ArrayList<>())
                .build();

        when(chatRepository.findAllByUser1(2)).thenReturn(List.of(chatEntity1));
        when(chatRepository.findAllByUser2(2)).thenReturn(List.of(chatEntity2));

        List<Chat> result = chatManager.getAllChatsCustomerService();

        List<Chat> expectedResult = List.of(chat1, chat2);

        assertEquals(expectedResult, result);
    }

    @Test
    void getAllChatsOfCustomerService_ThrowsException() {

        when(chatRepository.findAllByUser1(2)).thenThrow(WebSocketException.class);


        assertThrows(WebSocketException.class, () ->  chatManager.getAllChatsCustomerService());
    }

    @Test
    void getChatOfCustomer_Successfully(){
        Integer id = 5;

        ChatEntity chatEntity = ChatEntity.builder()
                .id(2)
                .user1(5)
                .user2(2)
                .messages(new ArrayList<>())
                .build();

        when(chatRepository.findByUser1(id)).thenReturn(chatEntity);
        when(chatRepository.findByUser2(id)).thenReturn(null);

        Chat result = chatManager.getChatOfCustomer(id);

        Chat expectedResult = Chat.builder()
                .id(2)
                .user_1(5)
                .user_2(2)
                .chatMessages(new ArrayList<>())
                .build();

        assertEquals(expectedResult, result);
    }

    @Test
    void getChatOfCustomer2_Successfully(){
        Integer id = 5;

        ChatEntity chatEntity = ChatEntity.builder()
                .id(2)
                .user1(5)
                .user2(2)
                .messages(new ArrayList<>())
                .build();

        when(chatRepository.findByUser1(id)).thenReturn(null);
        when(chatRepository.findByUser2(id)).thenReturn(chatEntity);

        Chat result = chatManager.getChatOfCustomer(id);

        Chat expectedResult = Chat.builder()
                .id(2)
                .user_1(5)
                .user_2(2)
                .chatMessages(new ArrayList<>())
                .build();

        assertEquals(expectedResult, result);
    }

    @Test
    void getNonexistantChatOfCustomer_Successfully(){
        Integer id = 5;


        ChatEntity newChatEntityReturn = ChatEntity.builder()
                .id(2)
                .user1(id)
                .user2(2)
                .messages(new ArrayList<>())
                .build();


        ChatEntity newChatEntity = ChatEntity.builder()
                .user1(id)
                .user2(2)
                .messages(new ArrayList<>())
                .build();

        when(chatRepository.findByUser1(id)).thenReturn(null);
        when(chatRepository.findByUser2(id)).thenReturn(null);
        when(chatRepository.saveAndFlush(newChatEntity)).thenReturn(newChatEntityReturn);

        Chat result = chatManager.getChatOfCustomer(id);

        Chat expectedResult = Chat.builder()
                .id(2)
                .user_1(5)
                .user_2(2)
                .chatMessages(new ArrayList<>())
                .build();

        assertEquals(expectedResult, result);
    }

}
