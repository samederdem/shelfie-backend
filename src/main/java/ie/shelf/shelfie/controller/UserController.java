package ie.shelf.shelfie;

import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{userId}")
    public UserResponseDto getUserDetails(@PathVariable Long userId) {
        return userService.getUserDetails(userId);
    }

    @GetMapping("/messages/overview/{userId}")
    public List<MessagesOverviewDto> getMessagesOverview(@PathVariable Long userId) {
        return userService.getMessagesOverview(userId);
    }

    @PostMapping("/user/edit/profile")
    public ResponseEntity<String> editUserProfile(@RequestBody User updatedUser)
    {
        return userService.editUserProfile(updatedUser);
    }

    @PostMapping("/user/auth/{token}")
    public ResponseEntity<?> authUser(@PathVariable String token)
    {
        return userService.authUser(token);
    }
}