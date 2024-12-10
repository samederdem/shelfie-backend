package ie.shelf.shelfie;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    public GroupResponseDto getGroupDetails(Long groupId) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found with ID: " + groupId));

        List<User> members = groupRepository.findUserByGroup(group);

        GroupResponseDto response = new GroupResponseDto(group.getId(), group.getName(), group.getPp(), group.getBio(), members);

        return response;
    }

    public ResponseEntity<String> createGroup(CreateGroupDto groupInfo)
    {
        Optional<User> optionalAdmin=userRepository.findById(groupInfo.getAdminId());
        if (optionalAdmin.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User for admin not found");

        User admin= optionalAdmin.get();

        groupRepository.save(new Group(admin, groupInfo.getName(), groupInfo.getPp(), groupInfo.getBio()));
        return ResponseEntity.ok("Group created successfully");


    }
}