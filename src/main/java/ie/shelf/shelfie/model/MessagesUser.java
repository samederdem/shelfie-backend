package ie.shelf.shelfie;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "messages_user")  // Explicit table name
public class MessagesUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender")  // Foreign Key to User table (sender)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "recv")  // Foreign Key to User table (receiver)
    private User recv;

    private String text;
    private String time;

    // Getters and Setters
}