package ie.shelf.shelfie;

//import ie.shelf.shelfie.dto.UserResponseDto;
//import ie.shelf.shelfie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public UserResponseDto getUserDetails(@PathVariable Long userId) {
        return userService.getUserDetails(userId);
    }
}