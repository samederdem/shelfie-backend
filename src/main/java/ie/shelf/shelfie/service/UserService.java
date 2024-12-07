package ie.shelf.shelfie;

//import ie.shelf.shelfie.dto.UserResponseDto;
//import ie.shelf.shelfie.dto.UserReviewDto;
//import ie.shelf.shelfie.model.User;
//import ie.shelf.shelfie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDto getUserDetails(Long userId) {
        // Fetch User details
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Fetch Most Read Genre
        String mostReadGenre = userRepository.findMostReadGenreByUserId(userId);

        // Fetch Reviews
        List<UserReviewDto> reviews = userRepository.findUserReviewsByUserId(userId);

        // Build the response DTO
        UserResponseDto response = new UserResponseDto(user.getId(), user.getName(), user.getPp(), user.getBio(), mostReadGenre, reviews);

        return response;
    }
}