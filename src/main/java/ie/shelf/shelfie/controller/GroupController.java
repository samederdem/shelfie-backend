package ie.shelf.shelfie;

//import ie.shelf.shelfie.dto.UserResponseDto;
//import ie.shelf.shelfie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/{groupId}")
    public GroupResponseDto getGroupDetails(@PathVariable Long groupId) {
        return groupService.getGroupDetails(groupId);
    }
}