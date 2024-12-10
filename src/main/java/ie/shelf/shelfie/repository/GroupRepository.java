package ie.shelf.shelfie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    
    @Query("SELECT user " +
        "FROM GroupUser " +
        "WHERE group= :group")
    List<User> findUserByGroup(Group group);

    @Modifying
    @Query(value = "INSERT INTO genre_group (group_id, genre_id) " +
               "SELECT :groupId, g.id " +
               "FROM genres g " +
               "WHERE g.id IN :genreIds", nativeQuery = true)
    void insertGenresGroup(List<Long> genreIds, Long groupId);

    @Modifying
    @Query(value = "INSERT INTO group_user (user_id, group_id) " +
               " VALUES(:adminId, :groupId)", nativeQuery = true)
    void insertGroupUser(Long adminId, Long groupId);
}
