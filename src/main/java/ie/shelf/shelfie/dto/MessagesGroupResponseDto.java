package ie.shelf.shelfie;
import java.util.List;

public class MessagesGroupResponseDto {

    private Group group;
    private List<MessageGroupDto> messages;


    public MessagesGroupResponseDto(Group group, List<MessageGroupDto> messages) {
        this.group=group;
        this.messages=messages;
    }

    public Group getGroup() {return group;}
    public void setGroup(Group group) {this.group=group;}

    public List<MessageGroupDto> getMessages() {return messages;}
    public void setMessages(List<MessageGroupDto> messages) {this.messages=messages;}
}