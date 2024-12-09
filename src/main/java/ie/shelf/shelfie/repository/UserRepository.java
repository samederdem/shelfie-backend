package ie.shelf.shelfie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
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
       "u.name, u.pp, " +
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
           "u.name, mg.text, mg.time, 'Group_message') " +
       "FROM MessagesGroup mg " +
       "JOIN mg.sender u " +
       "JOIN mg.recv g " +
       "JOIN GroupUser gu ON gu.group = g " +
       "WHERE gu.user.id = :userId " +
       "AND mg.time = (SELECT MAX(mg2.time) FROM MessagesGroup mg2 " +
                      "WHERE mg2.recv.id = g.id " +
                      "AND (mg2.sender.id = :userId OR mg2.recv.id = :userId))")
    List<MessagesOverviewDto> getMessagesOverviewGroup(Long userId);



}