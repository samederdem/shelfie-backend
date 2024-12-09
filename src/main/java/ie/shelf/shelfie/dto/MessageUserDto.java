package ie.shelf.shelfie;

public class MessageUserDto {
    private Integer sender;
    private String text;
    private String time;

    // Constructors, Getters, and Setters
    public MessageUserDto(Integer sender, String text, String time) {
        this.sender= sender;
        this.text = text;
        this.time= time;
    }

    /*
    public Long getId() {return id;}
    public void setId(Long id) {this.id=id;}

    public String getName() {return name;}
    public void setName(String name) {this.name=name;}

    public String getPp() {return pp;}
    public void setPp(String pp) {this.pp=pp;}

    public String getLastMessage() {return lastMessage;}
    public void setLastMessage(String lastMessage) {this.lastMessage=lastMessage;}

    public String getLastMessageSender() {return lastMessageSender;}
    public void setLastMessageSender(String lastMessageSender) {this.lastMessageSender=lastMessageSender;}

    public String getType() {return type;}
    public void setType(String type) {this.type=type;}
    */
}