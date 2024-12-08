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

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        String mostReadGenre = userRepository.findMostReadGenreByUserId(userId);

        List<UserReviewDto> reviews = userRepository.findUserReviewsByUserId(userId);

        UserResponseDto response = new UserResponseDto(user.getId(), user.getName(), user.getPp(), user.getBio(), mostReadGenre, reviews);

        return response;
    }
}