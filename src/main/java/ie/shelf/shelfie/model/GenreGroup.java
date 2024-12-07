package ie.shelf.shelfie;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "genre_group")  // Explicit table name
public class GenreGroup {

    @Id
    @ManyToOne
    @JoinColumn(name = "group_id")  // Foreign Key to Group table
    private Group group;

    @Id
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;  // Genre as part of the composite primary key

    // Getters and Setters
}