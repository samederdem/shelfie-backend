package ie.shelf.shelfie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;


    public GroupResponseDto getGroupDetails(Long groupId) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found with ID: " + groupId));

        List<User> members = groupRepository.findUserByGroup(group);

        GroupResponseDto response = new GroupResponseDto(group.getId(), group.getName(), group.getPp(), group.getBio(), members);

        return response;
    }
}