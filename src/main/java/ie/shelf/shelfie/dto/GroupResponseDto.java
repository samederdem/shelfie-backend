package ie.shelf.shelfie;

import java.util.List;

public class GroupResponseDto {
    private Long id;
    private String name;
    private String pp;
    private String bio;
    private List<User> members;

    // Constructors, Getters, and Setters
    public GroupResponseDto(Long id, String name, String pp, String bio, List<User> members) {
        this.id= id;
        this.name = name;
        this.pp = pp;
        this.bio= bio;
        this.members=members;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id=id;}

    public String getName() {return name;}
    public void setName(String name) {this.name=name;}

    public String getPp() {return pp;}
    public void setPp(String pp) {this.pp=pp;}

    public String getBio() {return bio;}
    public void setBio(String bio) {this.bio=bio;}

    public List<User> getMembers() {return members;}
    public void setMembers(List<User> members) {this.members=members;}
}