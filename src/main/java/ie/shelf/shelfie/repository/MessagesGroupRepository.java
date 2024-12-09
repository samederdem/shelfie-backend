package ie.shelf.shelfie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessagesGroupRepository extends JpaRepository<MessagesGroup, Long> {

    @Query("SELECT new ie.shelf.shelfie.MessageGroupDto(" +
           "   CASE WHEN mg.sender.id = :userId THEN 1 ELSE 0 END," +
           "   CASE WHEN mg.sender.id = :userId THEN 'You' ELSE u.name END, " +
           "    mg.text, mg.time) " +
           "FROM MessagesGroup mg " +
           "JOIN mg.sender u " +
           "WHERE mg.recv.id = :groupId " +
           "ORDER BY mg.time DESC")
    List<MessageGroupDto> getMessagesGroup(Long userId, Long groupId);

    
}