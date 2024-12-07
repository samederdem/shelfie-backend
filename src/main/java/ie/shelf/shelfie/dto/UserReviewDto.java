package ie.shelf.shelfie;

public class UserReviewDto {
    private String bookTitle;
    private String bookAuthor;
    private String text;
    private Integer rating;

    public UserReviewDto(String bookTitle, String bookAuthor, String text, Integer rating) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.text = text;
        this.rating = rating;
    }

    // Getters and Setters
}