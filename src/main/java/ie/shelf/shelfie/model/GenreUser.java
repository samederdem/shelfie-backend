package ie.shelf.shelfie;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "genre_user")  // Explicit table name
public class GenreGroup {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")  // Foreign Key to Group table
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;  // Genre as part of the composite primary key

    private Integer value;

    public GenreUser(){}
    public GenreUser(User user, Genre genre, Integer value)
    {
        this.user=user;
        this.genre=genre;
        this.value=value;
    }

}