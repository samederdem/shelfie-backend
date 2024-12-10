package ie.shelf.shelfie;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String pp;
    private String bio;

    @ManyToOne
    @JoinColumn(name = "admin")
    private User admin;

    public Group(){}
    public Group(User admin, String name, String pp, String bio)
    {
        this.admin=admin;
        this.name=name;
        this.pp=pp;
        this.bio=bio;
    }
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}

    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public String getPp(){return pp;}
    public void setPp(String pp){this.pp=pp;}

    public String getBio(){return bio;}
    public void setBio(String bio){this.bio=bio;}

    public User getAdmin(){return admin;}
    public void setAdmin(User admin){this.admin=admin;}

}