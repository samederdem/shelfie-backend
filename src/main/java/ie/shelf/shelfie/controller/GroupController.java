package ie.shelf.shelfie;

import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/{groupId}")
    public GroupResponseDto getGroupDetails(@PathVariable Long groupId) {
        return groupService.getGroupDetails(groupId);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createGroup(@RequestBody CreateGroupDto groupInfo)
    {
        return groupService.createGroup(groupInfo);
    }
    @PostMapping("/edit")
    public ResponseEntity<String> editGroupProfile(@RequestBody GroupMatchDto updatedGroup)
    {
        return groupService.editGroupProfile(updatedGroup);
    }
    @PostMapping("/kick/{groupId}/{userId}")
    public ResponseEntity<String> kickUser(@PathVariable Long groupId, @PathVariable Long userId)
    {
        return groupService.kickUser(groupId, userId);
    }
}
