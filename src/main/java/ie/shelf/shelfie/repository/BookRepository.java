package ie.shelf.shelfie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Transactional;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

/*
    @Query("SELECT new ie.shelf.shelfie.BookDto(b.id, b.name, b.author, AVG(r.rating), null " +
        "FROM Review r2 WHERE r2.book.id = b.id)) " +
        "FROM Book b " +
        "JOIN Review r ON r.book.id = b.id " +
        "WHERE b.id = :bookId " +
        "GROUP BY b.id")
    BookDto findBookDetailsById(Long bookId);*/

    @Query("SELECT new ie.shelf.shelfie.BookReviewDto( r.user.name,  r.text, r.rating ) " +
        "FROM Review r " +
        "JOIN r.book b " +
        "JOIN r.user u " +
        "WHERE r.book.id = :bookId")
    List<BookReviewDto> findReviewsByBookId(Long bookId);


    List<Book> findByNameContainingIgnoreCase(String searchStr);
/*
    @Query("SELECT new ie.shelf.shelfie.BookDto(b.id, b.name, b.author, AVG(r.rating), " +
           "(SELECT new ie.shelf.shelfie.UserReviewDto(r2.user.name, r2.text, r2.rating) " +
           "FROM Review r2 WHERE r2.book.id = b.id)) " +
           "FROM Book b " +
           "JOIN Review r ON r.book.id = b.id " +
           "GROUP BY b.id " +
           "ORDER BY AVG(r.rating) DESC")
    List<BookDto> findTopRatedBooks();

    @Query("SELECT new ie.shelf.shelfie.BookDto(b.id, b.name, b.author, AVG(r.rating), " +
           "(SELECT new ie.shelf.shelfie.UserReviewDto(r2.user.name, r2.text, r2.rating) " +
           "FROM Review r2 WHERE r2.book.id = b.id)) " +
           "FROM Book b " +
           "JOIN Review r ON r.book.id = b.id " +
           "WHERE r.user.id = :userId " +
           "GROUP BY b.id " +
           "ORDER BY AVG(r.rating) DESC")
    List<BookDto> findBooksReviewedByUser(Long userId);*/
    /*@Transactional
    @Modifying
    @Query(value = "INSERT INTO user_genre (user_id, genre_id, value) " +
                "SELECT :userId, bg.genre_id, :rating " +
                "FROM book_genre bg " +
                "WHERE bg.book_id = :bookId " +
                "ON CONFLICT (user_id, genre_id) DO UPDATE " +
                "SET value = user_genre.value + :rating", nativeQuery = true)
    void updateUserGenre(Long userId, Long bookId, int rating);*/
    //@Transactional
    @Modifying
    @Query(value = "INSERT INTO genre_user (user_id, genre_id, value) " +
                "SELECT :userId, bg.genre_id, :rating " +
                "FROM genre_book bg " +
                "WHERE bg.book_id = :bookId " +
                "ON CONFLICT (user_id, genre_id) DO UPDATE " +
                "SET value = genre_user.value + :rating", nativeQuery = true)
    int updateUserGenre(Long userId, Long bookId, Integer rating);

}
