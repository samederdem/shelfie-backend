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

    public String getBookTitle(){return bookTitle;}
    public void setBookTitle(String bookTitle){this.bookTitle=bookTitle;}

    public String getBookAuthor(){return bookAuthor;}
    public void getBookAuthor(String bookAuthor){this.bookAuthor=bookAuthor;}

    public String getText(){return text;}
    public void setText(String text){this.text=text;}

    public Integer getRating(){return rating;}
    public void setRating(Integer rating){this.rating=rating;}

    
}