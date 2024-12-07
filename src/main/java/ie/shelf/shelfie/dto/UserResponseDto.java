package ie.shelf.shelfie;

import java.util.List;

public class UserResponseDto {
    private Long id;
    private String name;
    private String pp;
    private String bio;
    private String mostReadGenre;
    private List<UserReviewDto> reviews;

    // Constructors, Getters, and Setters
    public UserResponseDto(Long id, String name, String pp, String bio, String mostReadGenre, List<UserReviewDto> reviews) {
        this.id= id;
        this.name = name;
        this.pp = pp;
        this.mostReadGenre = mostReadGenre;
        this.reviews=reviews;
    }
}