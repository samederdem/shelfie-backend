package ie.shelf.shelfie;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "genre_book")
public class GenreBook {

    @Id
    @ManyToOne
    @JoinColumn(name = "book_id")  // Foreign Key to Book table
    private Book book;

    @Id
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;  // Genre as part of the composite primary key

    public Book getBook(){return book;}
    public void setBook(Book book){this.book=book;}

    public Genre getGenre(){return genre;}
    public void setGenre(Genre genre){this.genre=genre;}

    // Getters and Setters
}