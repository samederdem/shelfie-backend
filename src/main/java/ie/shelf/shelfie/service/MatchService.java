package ie.shelf.shelfie;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class MatchService {

    @Autowired
    private UserRepository userRepository;

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
        return ResponseEntity.ok("matches_group table updates successfully");
    }

    @Transactional
    public ResponseEntity<String> rejectMatchUserGroup(Long userId, Long groupId)
    {
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
}
