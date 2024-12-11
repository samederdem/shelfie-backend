package ie.shelf.shelfie;

public class GroupMatchDto {
    private Long id;
    private String name;
    private String pp;
    private String bio;

    // Constructors, Getters, and Setters
    public GroupMatchDto() {}
    public GroupMatchDto(Long id, String name, String pp, String bio) {
        this.id= id;
        this.name = name;
        this.pp = pp;
        this.bio= bio;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id=id;}

    public String getName() {return name;}
    public void setName(String name) {this.name=name;}

    public String getPp() {return pp;}
    public void setPp(String pp) {this.pp=pp;}

    public String getBio() {return bio;}
    public void setBio(String bio) {this.bio=bio;}
}
