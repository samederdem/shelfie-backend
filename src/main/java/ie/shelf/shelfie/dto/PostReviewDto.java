package ie.shelf.shelfie;

public class PostReviewDto {
    private Long bookId;
    private Long userId;
    private Integer rating;
    private String text;

    // Constructors, Getters, and Setters
    /*
    public BookDto(Long id, String name, String author, Integer rating, List<BookReviewDto> reviews) {
        this.bookId = bookId;
        this.userId = userId;
        this.rating = rating;
        this.text = text;
    }
    */
    public Long getBookId() {return bookId;}
    public void setBookId(Long bookId) {this.bookId=bookId;}

    public Long getUserId() {return userId;}
    public void setUserId(Long userId) {this.userId=userId;}

    public String getText() {return text;}
    public void setText(String text) {this.text=text;}

    public Integer getRating() {return rating;}
    public void setRating(Integer rating) {this.rating=rating;}
}

