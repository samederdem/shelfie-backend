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
    public ResponseEntity<String> MatchUsers(@PathVariable Long userId1, @PathVariable Long userId2) {
        return matchService.MatchUsers(userId1, userId2);
    }

    @PostMapping("/user/reject/{userId1}/{userId2}")
    public ResponseEntity<String> RejectMatchUsers(@PathVariable Long userId1, @PathVariable Long userId2) {
        return matchService.RejectMatchUsers(userId1, userId2);
    }

    @PostMapping("/group/{userId}/{groupId}")
    public ResponseEntity<String> MatchUserGroup(@PathVariable Long userId, @PathVariable Long groupId) {
        return matchService.MatchUserGroup(userId, groupId);
    }
    
}