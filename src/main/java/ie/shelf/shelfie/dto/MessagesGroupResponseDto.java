package ie.shelf.shelfie;
import java.util.List;

public class MessagesGroupResponseDto {

    private SimpleGroupResponseDto group;
    private List<MessageGroupDto> messages;


    public MessagesGroupResponseDto(SimpleGroupResponseDto group, List<MessageGroupDto> messages) {
        this.group=group;
        this.messages=messages;
    }

    public SimpleGroupResponseDto getGroup() {return group;}
    public void setGroup(SimpleGroupResponseDto group) {this.group=group;}

    public List<MessageGroupDto> getMessages() {return messages;}
    public void setMessages(List<MessageGroupDto> messages) {this.messages=messages;}
}