package ie.shelf.shelfie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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


    @Query("SELECT new ie.shelf.shelfie.UserReviewDto(b.name, b.author, r.text, r.rating) " +
        "FROM Review r " +
        "JOIN r.book b " +
        "WHERE r.book.id = :bookId")
    List<UserReviewDto> findReviewsByBookId(Long bookId);

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

}
