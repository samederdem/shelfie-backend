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
}