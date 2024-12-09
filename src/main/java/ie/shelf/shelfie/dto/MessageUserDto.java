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

    
    public Integer getSender() {return sender;}
    public void setSender(Integer sender) {this.sender=sender;}

    public String getText() {return text;}
    public void setText(String text) {this.text=text;}

    public String getTime() {return time;}
    public void setTime(String time) {this.time=time;}
    
}