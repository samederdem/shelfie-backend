package ie.shelf.shelfie;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews")  // Explicit table name
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")  // Foreign Key to User table
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")  // Foreign Key to Book table
    private Book book;

    private Integer rating;  // Rating between 0 and 10
    private String text;


    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}

    public User getUser(){return user;}
    public void setUser(User user){this.user=user;}

    public Book getBook(){return book;}
    public void setBook(Book book){this.book=book;}

    public Integer getRating(){return rating;}
    public void setRating(Integer rating){this.rating=rating;}

    public String getText(){return text;}
    public void setText(String text){this.text=text;}
    // Getters and Setters
}