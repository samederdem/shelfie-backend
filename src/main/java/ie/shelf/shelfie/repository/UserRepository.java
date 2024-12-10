package ie.shelf.shelfie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT g.name FROM Genre g " +
        "JOIN GenreBook gb ON gb.genre.id = g.id " +
        "JOIN Review r ON r.book.id = gb.book.id " +
        "WHERE r.user.id = :userId " +
        "GROUP BY g.name " +
        "ORDER BY COUNT(g.name) DESC " +
        "LIMIT 1")
    String findMostReadGenreByUserId(Long userId);

    @Query("SELECT new ie.shelf.shelfie.UserReviewDto(b.name, b.author, r.text, r.rating) " +
           "FROM Review r " +
           "JOIN r.book b " +
           "WHERE r.user.id = :userId")
    List<UserReviewDto> findUserReviewsByUserId(Long userId);



    @Query("SELECT new ie.shelf.shelfie.MessagesOverviewDto(" +
        "CASE WHEN mu.sender.id = :userId THEN mu.recv.id ELSE mu.sender.id END, " +
        "CASE WHEN mu.sender.id = :userId THEN mu.recv.name ELSE mu.sender.name END, " +
        "CASE WHEN mu.sender.id = :userId THEN mu.recv.pp ELSE mu.sender.pp END, " +
        "CASE WHEN mu.sender.id = :userId THEN 'You' ELSE u.name END, " +
        "mu.text, mu.time, 'user') " +
        "FROM MessagesUser mu " +
        "JOIN mu.sender u " +
        "WHERE mu.sender.id = :userId OR mu.recv.id = :userId " +
        "AND mu.time = (SELECT MAX(mu2.time) FROM MessagesUser mu2 " +
        "WHERE (mu2.sender.id = :userId AND mu2.recv = mu.recv) OR (mu2.recv.id = :userId AND mu2.sender = mu.sender))")
    List<MessagesOverviewDto> getMessagesOverviewUser(Long userId);

    @Query("SELECT new ie.shelf.shelfie.MessagesOverviewDto(" +
          "g.id, g.name, g.pp, " +
           "u.name, mg.text, mg.time, 'group') " +
       "FROM MessagesGroup mg " +
       "JOIN mg.sender u " +
       "JOIN mg.recv g " +
       "JOIN GroupUser gu ON gu.group = g " +
       "WHERE gu.user.id = :userId " +
       "AND mg.time = (SELECT MAX(mg2.time) FROM MessagesGroup mg2 " +
                      "WHERE mg2.recv.id = g.id " +
                      "AND (mg2.sender.id = :userId OR mg2.recv.id = :userId))")
    List<MessagesOverviewDto> getMessagesOverviewGroup(Long userId);

    @Modifying
    @Query(value = "WITH match_check AS (" +
               "   SELECT * FROM matches " +
               "   WHERE (user1 = :id1 AND user2 = :id2) " +
               "   OR (user1 = :id2 AND user2 = :id1) " +
               "   FOR UPDATE SKIP LOCKED) " +
               "INSERT INTO matches (user1, user2, state) " +
               "SELECT :id1, :id2, 0 " +
               "WHERE NOT EXISTS (SELECT 1 FROM match_check) " +
               "ON CONFLICT (user1, user2) DO UPDATE " +
               "SET state = CASE " +
               "   WHEN matches.state = 0 AND matches.user1 = :id2 AND matches.user2 = :id1 THEN 1 " +
               "   ELSE matches.state " +
               "END", nativeQuery = true)
    void createOrUpdateMatch(Long id1, Long id2);

}
