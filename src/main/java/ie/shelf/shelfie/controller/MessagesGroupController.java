package ie.shelf.shelfie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessagesGroupController {

    @Autowired
    private MessagesGroupService messagesGroupService;

    
    @GetMapping("/group/{userId}/{groupId}")
    public MessagesGroupResponseDto getMessagesGroup(@PathVariable Long userId, @PathVariable Long groupId) {
        return messagesGroupService.getMessagesGroup(userId, groupId);
    }
    

}