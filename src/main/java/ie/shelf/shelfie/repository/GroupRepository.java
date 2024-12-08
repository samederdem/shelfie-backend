package ie.shelf.shelfie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    
    @Query("SELECT user " +
        "FROM GroupUser " +
        "WHERE group= :group")
    List<User> findUserByGroup(Group group);
    
}