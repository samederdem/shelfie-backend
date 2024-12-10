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
        userRepository.createOrUpdateMatch(userId1, userId2);
        return ResponseEntity.ok("Matches table updates successfully");
    }
    
}
