package ie.shelf.shelfie;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;
import java.util.Comparator;

@Service
public class MatchService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessagesUserService messagesUserService;
    //@Autowired
    //private UserService userService;

    @Transactional
    public ResponseEntity<String> matchUsers(Long id1, Long id2)
    {
        if (id1.equals(id2)) {
            throw new IllegalArgumentException("User cannot match with itself");
        }

        // Check if a match exists
        Optional<Match> existingMatch = userRepository.findMatch(id1, id2);

        if (existingMatch.isEmpty()) {
            // No match exists, insert a new match with state = 0
            userRepository.insertMatch(id1, id2);
        } else {
            // Match exists, update state if it's 0
            String matchStr = "It's a match!";
            SendMessageDto tmp = new SendMessageDto(id1,id2, matchStr);
            messagesUserService.sendMessageUser(tmp);
            userRepository.updateMatchState(id1, id2);
        }

        return ResponseEntity.ok("Matches table updates successfully");
    }


    @Transactional
    public ResponseEntity<String> rejectMatchUsers(Long id1, Long id2)
    {
        if (id1.equals(id2)) {
            throw new IllegalArgumentException("User cannot reject itself");
        }

        // Check if a match exists
        Optional<Match> existingMatch = userRepository.findMatch(id1, id2);

        if (existingMatch.isEmpty()) {
            userRepository.insertMatchReject(id1, id2);
        } else {
            userRepository.updateMatchReject(id1, id2);
        }
        return ResponseEntity.ok("Matches table updates successfully");

    }
    
    @Transactional
    public ResponseEntity<String> matchUserGroup(Long userId, Long groupId)
    {

        // Check if a match exists
        Optional<MatchGroup> existingMatch = userRepository.findMatchGroup(userId, groupId);

        if (existingMatch.isEmpty()) {
            // No match exists, insert a new match with state = 0
            userRepository.insertMatchGroup(userId, groupId);
        } else {
            // Match exists, update state if it's 0
            userRepository.updateMatchGroupState(userId, groupId);
        }
        return ResponseEntity.ok("matches_group table updates successfully");
    }

    @Transactional
    public ResponseEntity<String> rejectMatchUserGroup(Long userId, Long groupId)
    {
            Optional<MatchGroup> existingMatch = userRepository.findMatchGroup(userId, groupId);

        if (existingMatch.isEmpty()) {
            // No match exists, insert a new match with state = 0
            userRepository.insertMatchGroupReject(userId, groupId);
        } else {
            // Match exists, update state if it's 0

            userRepository.updateMatchGroupReject(userId, groupId);
        }
        return ResponseEntity.ok("matches_group table updates successfully");
    }

    public List<MatchRequestDto> getMatchRequests(Long userId)
    {
        List<MatchRequestDto> userMatches = userRepository.getMatchRequestsUser(userId);
        List<MatchRequestDto> groupMatches = userRepository.getMatchRequestsGroup(userId);
        List<MatchRequestDto> combinedMatches = new ArrayList<>();
        combinedMatches.addAll(userMatches);
        combinedMatches.addAll(groupMatches);
        return combinedMatches;
    }

    public ResponseEntity<?> getMatchUser(Long userId)
    {
        Optional<User> optionalUser = userRepository.getMatchUser(userId);
        if(optionalUser.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No User for match found");
        }
        return ResponseEntity.ok(optionalUser.get());
    }

    public ResponseEntity<?> getMatchGroup(Long userId)
    {
        List<Object[]> results = userRepository.getMatchGroup(userId);

        if (results.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No group for match found");
        }

        Object[] row = results.get(0);  // Get the first row (or you could loop through if needed)

        Long groupId = (Long) row[0];
        String name = (String) row[1];
        String bio = (String) row[2];
        String pp = (String) row[3];

        return ResponseEntity.ok(new GroupMatchDto(groupId, name, pp, bio));

    }
}
    

