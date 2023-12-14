package individual.individualsem3backend.business;

import individual.individualsem3backend.domain.*;

import java.util.List;

public interface ChatManager {
    ChatMessage sendMessage(ChatMessage message);
    Chat getChat(Integer id);
    List<Chat> getAllChatsCustomerService();
}
