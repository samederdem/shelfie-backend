package ie.shelf.shelfie;

public class MatchRequestDto {
    private User user;
    private Group group;

    // Constructors, Getters, and Setters
    public MatchRequestDto(User user, Group group) {
        this.user= user;
        this.group=group;
    }

    public MatchRequestDto(User user) {
        this.user= user;
    }

    
    public User getUser() {return user;}
    public void setUser(User user) {this.user=user;}

    public Group getGroup() {return group;}
    public void setGroup(Group group) {this.group=group;}
    
}