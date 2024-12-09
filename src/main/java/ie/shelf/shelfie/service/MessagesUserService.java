package ie.shelf.shelfie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagesUserService {

    @Autowired
    private MessagesUserRepository messagesUserRepository;

    @Autowired
    private UserRepository userRepository;
    
    public MessagesUserResponseDto getMessagesUser(Long userId, Long partnerId) {
        User partner = userRepository.findById(partnerId)
            .orElseThrow(() -> new RuntimeException("User not found with ID: " + partnerId));
        List<MessageUserDto> messages = messagesUserRepository.getMessagesUser(userId, partnerId);
        return new MessagesUserResponseDto(partner, messages);

    }

    public MessagesUser sendMessageUser(SendMessageDto message) {
        User sender=userRepository.findById(message.getSender()).orElseThrow(() -> new RuntimeException());
        User recv=userRepository.findById(message.getRecv()).orElseThrow(() -> new RuntimeException());
        
        return messagesUserRepository.save(new MessagesUser(recv, sender, message.getText()));
    }
    

}
