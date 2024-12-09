package ie.shelf.shelfie;

public class SendMessageUserDto {
    private Long sender;
    private Long recv;
    private String text;

    // Constructors, Getters, and Setters
    public SendMessageUserDto(Long sender, Long recv, String text) {
        this.sender= sender;
        this.recv=recv;
        this.text = text;
    }

    
    public Long getSender() {return sender;}
    public void setSender(Long sender) {this.sender=sender;}

    public String getText() {return text;}
    public void setText(String text) {this.text=text;}

    public Long getRecv() {return recv;}
    public void setRecv(Long recv) {this.recv=recv;}
    
}