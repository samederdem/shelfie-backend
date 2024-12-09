package ie.shelf.shelfie;
import java.util.List;

public class MessagesUserResponseDto {

    private User partner;
    private List<MessageUserDto> messages;


    public MessagesUserResponseDto(User partner, List<MessageUserDto> messages) {
        this.partner=partner;
        this.messages=messages;
    }

    public User getPartner() {return partner;}
    public void setPartner(User partner) {this.partner=partner;}

    public List<MessageUserDto> getMessages() {return messages;}
    public void setMessages(List<MessageUserDto> messages) {this.messages=messages;}
}