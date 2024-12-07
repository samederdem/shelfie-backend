package ie.shelf.shelfie;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")  // Explicit table name
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String pp;  // Profile Picture URL or path
    private String bio;

    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}

    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public String getPp(){return pp;}
    public void setPp(String pp){this.pp=pp;}

    public String getBio(){return bio;}
    public void setBio(String bio){this.bio=bio;}

    // Getters and Setters
}