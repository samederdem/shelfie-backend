package ie.shelf.shelfie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import java.util.List;
import java.util.Optional;


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


    @Query("SELECT new ie.shelf.shelfie.Match(m.user1, m.user2, m.state) FROM Match m WHERE (m.user1.id = :id1 AND m.user2.id = :id2) OR (m.user1.id = :id2 AND m.user2.id = :id1)")
    Optional<Match> findMatch(Long id1, Long id2);

    // Native query to insert a new match if no existing match is found

    @Modifying
    @Query(value = "INSERT INTO matches (user1_id, user2_id, state) VALUES (:id1, :id2, 0)", nativeQuery = true)
    void insertMatch(Long id1, Long id2);
    // Native query to update the state to 1 if a match with state = 0 exists
    @Modifying
    @Query(value = "UPDATE matches SET state = 1 WHERE (user1_id = :id1 AND user2_id = :id2 OR user1_id = :id2 AND user2_id = :id1) AND state = 0", nativeQuery = true)
    void updateMatchState(Long id1, Long id2);

    @Modifying
    @Query(value = "INSERT INTO matches (user1_id, user2_id, state) VALUES (:id1, :id2, -1)", nativeQuery = true)
    void insertMatchReject(Long id1, Long id2);

    @Modifying
    @Query(value = "UPDATE matches SET state = -1 WHERE (user1_id = :id1 AND user2_id = :id2 OR user1_id = :id2 AND user2_id = :id1)", nativeQuery = true)
    void updateMatchReject(Long id1, Long id2);

    @Query("SELECT new ie.shelf.shelfie.MatchGroup(m.user, m.group, m.state) FROM MatchGroup m WHERE m.user.id = :user AND m.group.id = :group")
    Optional<MatchGroup> findMatchGroup(Long user, Long group);

    // Native query to insert a new match if no existing match is found
    @Modifying
    @Query(value = "INSERT INTO matches_group (user_id, group_id, state) VALUES (:user, :group, 0)", nativeQuery = true)
    void insertMatchGroup(Long user, Long group);

    // Native query to update the state to 1 if a match with state = 0 exists
    @Modifying
    @Query(value = "UPDATE matches_group SET state = 1 WHERE user_id = :user AND group_id = :group AND state = 0", nativeQuery = true)
    void updateMatchGroupState(Long user, Long group);

    @Modifying
    @Query(value = "INSERT INTO matches_group (user_id, group_id, state) VALUES (:user, :group, -1)", nativeQuery = true)
    void insertMatchGroupReject(Long user, Long group);

    @Modifying
    @Query(value = "UPDATE matches_group SET state = -1 WHERE (user_id = :user AND group_id = :group)", nativeQuery = true)
    void updateMatchGroupReject(Long user, Long group);


    @Query("SELECT new ie.shelf.shelfie.MatchRequestDto(m.user1) FROM Match m WHERE m.user2.id=:userId AND m.state=0")
    List<MatchRequestDto> getMatchRequestsUser(Long userId);

    @Query("SELECT new ie.shelf.shelfie.MatchRequestDto(mg.user, mg.group) FROM MatchGroup mg WHERE mg.group.admin.id=:userId AND mg.state=0")
    List<MatchRequestDto> getMatchRequestsGroup(Long userId);

    String q = "WITH user_vector AS ( " +
        "    SELECT ug.user_id, ug.genre_id, COALESCE(SUM(ug.value), 0) / COUNT(r.id) AS normalized_value " +
        "    FROM genre_user ug " +
        "    LEFT JOIN reviews r ON r.user_id = ug.user_id " +
        "    WHERE ug.user_id = :userId " +
        "    GROUP BY ug.user_id, ug.genre_id), " +
        "other_users_vectors AS ( " +
        "    SELECT ug.user_id, ug.genre_id, COALESCE(SUM(ug.value), 0) / COUNT(r.id) AS normalized_value " +
        "    FROM genre_user ug " +
        "    LEFT JOIN reviews r ON r.user_id = ug.user_id " +
        "    WHERE ug.user_id != :userId " +
        "    GROUP BY ug.user_id, ug.genre_id), " +
        "distance_calculation AS ( " +
        "    SELECT o.user_id AS other_user_id, SUM(POWER(u.normalized_value - o.normalized_value, 2)) AS squared_diff_sum " +
        "    FROM user_vector u " +
        "    JOIN other_users_vectors o ON u.genre_id = o.genre_id " +
        "    GROUP BY o.user_id), " +
        "closest_users AS ( " +
        "    SELECT dc.other_user_id, SQRT(dc.squared_diff_sum) AS distance " +
        "    FROM distance_calculation dc) " +
        "SELECT u.id, u.name, u.bio, u.pp " +
        "FROM closest_users cu " +
        "JOIN users u ON u.id = cu.other_user_id " +
        "ORDER BY cu.distance ASC " +
        "LIMIT 1";
    @Query(value = q, nativeQuery = true)
    Optional<User> getMatchUser(Long userId);

    @Query("SELECT gu.group FROM GroupUser gu WHERE gu.user.id=:userId")//placeholder
    Optional<Group> getMatchGroup(Long userId);

}
