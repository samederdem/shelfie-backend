package ie.shelf.shelfie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagesGroupService {

    @Autowired
    private MessagesGroupRepository messagesGroupRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;
    
    public MessagesGroupResponseDto getMessagesGroup(Long userId, Long groupId) {
        Group group = groupRepository.findById(groupId)
            .orElseThrow(() -> new RuntimeException("Group not found with ID: " + groupId));
        List<MessageGroupDto> messages = messagesGroupRepository.getMessagesGroup(userId, groupId);
        return new MessagesGroupResponseDto(group, messages);

    }

    public MessagesGroup sendMessageGroup(SendMessageDto message) {
        User sender=userRepository.findById(message.getSender()).orElseThrow(() -> new RuntimeException());
        Group recv=groupRepository.findById(message.getRecv()).orElseThrow(() -> new RuntimeException());
        
        return messagesGroupRepository.save(new MessagesGroup(recv, sender, message.getText()));
    }
    

}
