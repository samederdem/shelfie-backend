package ie.shelf.shelfie;


public class CreateGroupDto {
    private Long adminId;
    private String name;
    private String pp;
    private String bio;


    public Long getAdminId() {return adminId;}
    public void setAdminId(Long adminId) {this.adminId=adminId;}

    public String getName() {return name;}
    public void setName(String name) {this.name=name;}

    public String getPp() {return pp;}
    public void setPp(String pp) {this.pp=pp;}

    public String getBio() {return bio;}
    public void setBio(String bio) {this.bio=bio;}
}