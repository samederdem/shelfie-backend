package ie.shelf.shelfie;

public class MessageGroupDto {
    private Integer sender;
    private String senderName;
    private String text;
    private String time;

    // Constructors, Getters, and Setters
    public MessageGroupDto(Integer sender, String senderName, String text, String time) {
        this.sender= sender;
        this.senderName=senderName;
        this.text = text;
        this.time= time;
    }

    
    public Integer getSender() {return sender;}
    public void setSender(Integer sender) {this.sender=sender;}

    public String getText() {return text;}
    public void setText(String text) {this.text=text;}

    public String getSenderName() {return senderName;}
    public void setSenderName(String senderName) {this.senderName=senderName;}

    public String getTime() {return time;}
    public void setTime(String time) {this.time=time;}
    
}