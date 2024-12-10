package ie.shelf.shelfie;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "matches")
public class Match {
    public Match(){}
    public Match(User user1, User user2, Integer state){
        this.user1=user1;
        this.user2=user2;
        this.state=state;
    }
    @Id
    @ManyToOne
    @JoinColumn(name = "user1_id")  // Foreign Key to Book table
    private User user1;

    @Id
    @ManyToOne
    @JoinColumn(name = "user2_id")
    private User user2;  // Genre as part of the composite primary key
    private Integer state;

    public User getUser1(){return user1;}
    public void setUser1(User user1){this.user1=user1;}

    public User getUser2(){return user2;}
    public void setUser2(User user2){this.user2=user2;}

    public Integer getState(){return state;}
    public void setState(Integer state){this.state=state;}

    // Getters and Setters
}
