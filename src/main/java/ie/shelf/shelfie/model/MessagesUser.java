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

    @CreationTimestamp
    private String time;

    public User getSender(){return sender;}
    public void setSender(User sender){this.sender=sender;}

    public User getRecv(){return recv;}
    public void setRecv(User recv){this.recv=recv;}

    public String getText(){return text;}
    public void setText(String text){this.text=text;}

}