package ie.shelf.shelfie;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "messages_group")  // Explicit table name
public class MessagesGroup {

    public MessagesGroup(){}
    public MessagesGroup(Group recv, User sender, String text)
    {
        this.recv=recv;
        this.sender=sender;
        this.text=text;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender")  // Foreign Key to User table (sender)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "recv")  // Foreign Key to Group table (group receiver)
    private Group recv;

    private String text;

    @CreationTimestamp
    private String time;
}