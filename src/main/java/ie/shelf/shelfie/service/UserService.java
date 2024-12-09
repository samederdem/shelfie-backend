package ie.shelf.shelfie;

//import ie.shelf.shelfie.dto.UserResponseDto;
//import ie.shelf.shelfie.dto.UserReviewDto;
//import ie.shelf.shelfie.model.User;
//import ie.shelf.shelfie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
}