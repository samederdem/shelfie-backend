package ie.shelf.shelfie;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "group_user")  // Explicit table name
public class GroupUser {

    @Id
    @ManyToOne
    @JoinColumn(name = "group_id")  // Foreign Key to Group table
    private Group group;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")  // Foreign Key to User table
    private User user;

    // Getters and Setters
}