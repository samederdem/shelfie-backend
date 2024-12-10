package ie.shelf.shelfie;

public class BookReviewDto {
    private String name;
    private String bookAuthor;
    private String text;
    private Integer rating;

    public BookReviewDto(String name, String text, Integer rating) {
        this.name = name;
        this.text = text;
        this.rating = rating;
    }

    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public String getText(){return text;}
    public void setText(String text){this.text=text;}

    public Integer getRating(){return rating;}
    public void setRating(Integer rating){this.rating=rating;}

    
}
