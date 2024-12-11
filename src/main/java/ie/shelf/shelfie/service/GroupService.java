package ie.shelf.shelfie;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MessagesGroupService messagesGroupService;

    public GroupResponseDto getGroupDetails(Long groupId) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found with ID: " + groupId));

        List<User> members = groupRepository.findUserByGroup(group);

        GroupResponseDto response = new GroupResponseDto(group.getId(), group.getName(), group.getPp(), group.getBio(), group.getAdmin().getId(), members);

        return response;
    }

    @Transactional
    public ResponseEntity<String> createGroup(CreateGroupDto groupInfo)
    {
        Optional<User> optionalAdmin=userRepository.findById(groupInfo.getAdminId());
        if (optionalAdmin.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User for admin not found");

        User admin= optionalAdmin.get();

        Group group = groupRepository.save(new Group(admin, groupInfo.getName(), groupInfo.getPp(), groupInfo.getBio()));

        groupRepository.insertGroupUser(admin.getId(), group.getId());
        
        groupRepository.insertGenresGroup(groupInfo.getGenres(), group.getId());
        
        //messagesGroupService.sendMessageGroup(new SendMessageDto(admin.getId(), group.getId(), "Welcome to "+group.getName()));
        return ResponseEntity.ok("Group created successfully");


    }

    public ResponseEntity<String> editGroupProfile(GroupMatchDto updatedGroup)
    {
        Optional<Group> optionalGroup = groupRepository.findById(updatedGroup.getId());

        if (optionalGroup.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Group not found");
        }

        Group existingGroup = optionalGroup.get();
        if (updatedGroup.getBio() != null) {
        existingGroup.setBio(updatedGroup.getBio());
        }
        if (updatedGroup.getName() != null) {
        existingGroup.setName(updatedGroup.getName());
        }
        if (updatedGroup.getPp() != null) {
        existingGroup.setPp(updatedGroup.getPp());
        }

        groupRepository.save(existingGroup);

        return ResponseEntity.ok("Group updated successfully");
    }

    @Transactional
    public ResponseEntity<String> kickUser(Long groupId, Long userId)
    {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);

        if (optionalGroup.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Group not found");
        }
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        Group  existingGroup = optionalGroup.get();
        User  existingUser = optionalUser.get();
        if (existingGroup.getAdmin().getId() == existingUser.getId()){
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Kicking admin from group not allowed");
        }

        Integer isMember= groupRepository.findMemberInGroup(existingGroup, existingUser);
        if (isMember == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found in group");
        }
        groupRepository.deleteMember(existingGroup, existingUser);

        return ResponseEntity.ok("Group updated successfully");
    }


}
