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
        this.bio= bio;
        this.mostReadGenre = mostReadGenre;
        this.reviews=reviews;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id=id;}

    public String getName() {return name;}
    public void setName(String name) {this.name=name;}

    public String getPp() {return pp;}
    public void setPp(String pp) {this.pp=pp;}

    public String getBio() {return bio;}
    public void setBio(String bio) {this.bio=bio;}

    public String getMostReadGenre() {return mostReadGenre;}
    public void setMostReadGenre(String mostReadGenre) {this.mostReadGenre=mostReadGenre;}

    public List<UserReviewDto> getReviews() {return reviews;}
    public void setReviews(List<UserReviewDto> reviews) {this.reviews=reviews;}
}