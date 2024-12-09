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
        List<MessageUserDto> messages = messagesUserRepository.getMessagesUser(userId, userId);
        return new MessagesUserResponseDto(partner, messages);

    }
    

}