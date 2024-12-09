package ie.shelf.shelfie;

public class MessagesOverviewDto {
    private Long id;
    private String name;
    private String pp;
    private String lastMessageSender;
    private String lastMessage;
    private String type;
    private String time;

    // Constructors, Getters, and Setters
    public MessagesOverviewDto(Long id, String name, String pp, String lastMessageSender, String lastMessage, String time, String type) {
        this.id= id;
        this.name = name;
        this.pp = pp;
        this.lastMessage= lastMessage;
        this.lastMessageSender=lastMessageSender;
        this.time=time;
        this.type = type;
    }

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

    public String getTime() {return time;}
    public void setTime(String time) {this.time=time;}
}