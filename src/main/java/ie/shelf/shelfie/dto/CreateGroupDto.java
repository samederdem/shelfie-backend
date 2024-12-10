package ie.shelf.shelfie;


import java.util.List;


public class CreateGroupDto {
    private Long adminId;
    private String name;
    private String pp;
    private String bio;
    private List<Long> genres;


    public Long getAdminId() {return adminId;}
    public void setAdminId(Long adminId) {this.adminId=adminId;}

    public String getName() {return name;}
    public void setName(String name) {this.name=name;}

    public String getPp() {return pp;}
    public void setPp(String pp) {this.pp=pp;}

    public String getBio() {return bio;}
    public void setBio(String bio) {this.bio=bio;}

    public List<Long> getGenres() {return genres;}
    public void setGenres(List<Long> genres) {this.genres=genres;}

}