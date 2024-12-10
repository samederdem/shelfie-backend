package ie.shelf.shelfie;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Optional;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDto getUserDetails(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        String mostReadGenre = userRepository.findMostReadGenreByUserId(userId);

        List<UserReviewDto> reviews = userRepository.findUserReviewsByUserId(userId);

        UserResponseDto response = new UserResponseDto(user.getId(), user.getName(), user.getPp(), user.getBio(), mostReadGenre, reviews);

        return response;
    }

    public List<MessagesOverviewDto> getMessagesOverview(Long userId) {
        List<MessagesOverviewDto> userMessages = userRepository.getMessagesOverviewUser(userId);
        List<MessagesOverviewDto> groupMessages = userRepository.getMessagesOverviewGroup(userId);
        
        // Combine the two lists
        List<MessagesOverviewDto> combinedMessages = new ArrayList<>();
        combinedMessages.addAll(userMessages);
        combinedMessages.addAll(groupMessages);
        
        // Optionally, you can sort combinedMessages if needed
        combinedMessages.sort(Comparator.comparing(MessagesOverviewDto::getTime));

        List<MessagesOverviewDto> uniqueMessages = combinedMessages.stream()
        .collect(Collectors.toMap(
            MessagesOverviewDto::getId,  // Key: id
            message -> message,           // Value: the message itself
            (existing, replacement) -> existing // If duplicate, keep the first occurrence
        ))
        .values()
        .stream()
        .collect(Collectors.toList());

        return uniqueMessages;

    }
    public ResponseEntity<String> editUserProfile(User updatedUser)
    {
        Optional<User> optionalUser = userRepository.findById(updatedUser.getId());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User existingUser = optionalUser.get();
         if (updatedUser.getBio() != null) {
            existingUser.setBio(updatedUser.getBio());
        }
        if (updatedUser.getPp() != null) {
            existingUser.setPp(updatedUser.getPp());
        }

        userRepository.save(existingUser);

        return ResponseEntity.ok("User updated successfully");
    }
    
}