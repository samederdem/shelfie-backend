package ie.shelf.shelfie;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ResponseEntity<String> MatchUsers(Long userId1, Long userId2)
    {
        handleMatch(userId1, userId2);
        return ResponseEntity.ok("Matches table updates successfully");
    }
    void handleMatch(Long id1, Long id2) {
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
    }
    
}
