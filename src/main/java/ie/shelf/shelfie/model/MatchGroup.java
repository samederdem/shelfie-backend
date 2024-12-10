package ie.shelf.shelfie;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "matches_group")
public class MatchGroup {
    public MatchGroup(){}
    public MatchGroup(User user, Group group, Integer state){
        this.user=user;
        this.group=group;
        this.state=state;
    }
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")  // Foreign Key to Book table
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;  // Genre as part of the composite primary key
    private Integer state;

    public User getUser(){return user;}
    public void setUser(User user){this.user=user;}

    public Group getGroup(){return group;}
    public void setGroup(Group group){this.group=group;}

    public Integer getState(){return state;}
    public void setState(Integer state){this.state=state;}

    // Getters and Setters
}