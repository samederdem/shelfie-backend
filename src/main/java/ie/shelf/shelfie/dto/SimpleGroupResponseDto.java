package ie.shelf.shelfie;

import java.util.List;

public class SimpleGroupResponseDto {
    private Long id;
    private String name;
    private String pp;
    private String bio;
    private Long admin;

    // Constructors, Getters, and Setters
    public SimpleGroupResponseDto(Long id, String name, String pp, String bio,Long admin) {
        this.id= id;
        this.name = name;
        this.pp = pp;
        this.bio= bio;
        this.admin = admin;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id=id;}

    public String getName() {return name;}
    public void setName(String name) {this.name=name;}

    public String getPp() {return pp;}
    public void setPp(String pp) {this.pp=pp;}

    public String getBio() {return bio;}
    public void setBio(String bio) {this.bio=bio;}

    public Long getAdmin() {return admin;}
    public void setAdmin(Long admin) {this.admin=admin;}
}
