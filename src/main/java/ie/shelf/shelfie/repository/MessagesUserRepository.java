package ie.shelf.shelfie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessagesUserRepository extends JpaRepository<MessagesUser, Long> {

    @Query("SELECT new ie.shelf.shelfie.MessageUserDto(" +
           "   CASE WHEN mu.sender.id = :userId AND mu.recv.id = :partnerId THEN 1 " +
           "        WHEN mu.sender.id = :partnerId AND mu.recv.id = :userId THEN 0 " +
           "   END, mu.text, mu.time) " +
           "FROM MessagesUser mu " +
           "WHERE (mu.sender.id = :userId AND mu.recv.id = :partnerId) " +
           "   OR (mu.sender.id = :partnerId AND mu.recv.id = :userId) " +
           "ORDER BY mu.time DESC")
    List<MessageUserDto> getMessagesUser(Long userId, Long partnerId);

    
}