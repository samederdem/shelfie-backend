package ie.shelf.shelfie;

import java.util.List;

public class BookDto {
    private Long id;
    private String name;
    private String author;
    private Integer rating;
    private List<UserReviewDto> reviews;

    // Constructors, Getters, and Setters
    public BookDto(Long id, String name, String author, Integer rating, List<UserReviewDto> reviews) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.rating = rating;
        this.reviews = reviews;
    }
    public Long getId() {return id;}
    public void setId(Long id) {this.id=id;}

    public String getAuthor() {return author;}
    public void setAuthor(String author) {this.author=author;}

    public String getName() {return name;}
    public void setName(String name) {this.name=name;}

    public Integer getRating() {return rating;}
    public void setRating(Integer rating) {this.rating=rating;}

    public List<UserReviewDto> getReviews() {return reviews;}
    public void setReviews(List<UserReviewDto> reviews) {this.reviews=reviews;}
}

