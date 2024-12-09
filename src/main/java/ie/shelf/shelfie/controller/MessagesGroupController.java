package ie.shelf.shelfie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessagesGroupController {

    @Autowired
    private MessagesGroupService messagesGroupService;

    
    @GetMapping("/group/{userId}/{groupId}")
    public MessagesGroupResponseDto getMessagesGroup(@PathVariable Long userId, @PathVariable Long groupId) {
        return messagesGroupService.getMessagesGroup(userId, groupId);
    }
    
    
    @PostMapping("group/send")
    public MessagesGroup sendMessageUser(@RequestBody SendMessageDto message) {
        return messagesGroupService.sendMessageGroup(message);
    }

}