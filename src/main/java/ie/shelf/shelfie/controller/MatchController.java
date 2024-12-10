package ie.shelf.shelfie;

import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping("/user/{userId1}/{userId2}")
    public ResponseEntity<String> matchUsers(@PathVariable Long userId1, @PathVariable Long userId2) {
        return matchService.matchUsers(userId1, userId2);
    }

    @PostMapping("/user/reject/{userId1}/{userId2}")
    public ResponseEntity<String> rejectMatchUsers(@PathVariable Long userId1, @PathVariable Long userId2) {
        return matchService.rejectMatchUsers(userId1, userId2);
    }

    @PostMapping("/group/{userId}/{groupId}")
    public ResponseEntity<String> matchUserGroup(@PathVariable Long userId, @PathVariable Long groupId) {
        return matchService.matchUserGroup(userId, groupId);
    }

    @PostMapping("/group/reject/{userId}/{groupId}")
    public ResponseEntity<String> rejectMatchUserGroup(@PathVariable Long userId, @PathVariable Long groupId) {
        return matchService.rejectMatchUserGroup(userId, groupId);
    }

    @GetMapping("/all/user/{userId}")
    public List<User> getMatchRequestsUser(@PathVariable Long userId) {
        return matchService.getMatchRequestsUser(userId);
    }

    @GetMapping("/all/group/{userId}")
    public List<MatchRequestDto> getMatchRequestsGroup(@PathVariable Long userId) {
        return matchService.getMatchRequestsGroup(userId);
    }
    
}