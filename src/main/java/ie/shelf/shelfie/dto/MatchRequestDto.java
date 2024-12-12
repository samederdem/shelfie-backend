package ie.shelf.shelfie;

public class MatchRequestDto {
    private User user;
    private SimpleGroupResponseDto group;

    // Constructors, Getters, and Setters
    public MatchRequestDto(User user, SimpleGroupResponseDto group) {
        this.user= user;
        this.group=group;
    }

    public MatchRequestDto(User user) {
        this.user= user;
    }

    
    public User getUser() {return user;}
    public void setUser(User user) {this.user=user;}

    public SimpleGroupResponseDto getGroup() {return group;}
    public void setGroup(SimpleGroupResponseDto group) {this.group=group;}
    
}