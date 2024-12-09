package ie.shelf.shelfie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessagesUserController {

    @Autowired
    private MessagesUserService messagesUserService;

    
    @GetMapping("/user/{userId}/{partnerId}")
    public MessagesUserResponseDto getMessagesUser(@PathVariable Long userId, @PathVariable Long partnerId) {
        return messagesUserService.getMessagesUser(userId, partnerId);
    }

    @PostMapping("user/send")
    public MessagesUser sendMessageUser(@RequestBody SendMessageUserDto message) {
        return messagesUserService.sendMessageUser(message);
    }
    

}