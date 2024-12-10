package ie.shelf.shelfie;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    public BookDto getBookDetails(Long bookId) {
        // Fetch Book details from the BookRepository
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + bookId));

        // Fetch Reviews
        List<BookReviewDto> reviews = bookRepository.findReviewsByBookId(bookId);
        Integer rating = calculateAverageRating(reviews);
        // Build the response DTO
        BookDto response = new BookDto(book.getId(), book.getName(), book.getAuthor(), rating, reviews);
        return response;
    }

    private Integer calculateAverageRating(List<BookReviewDto> reviews) {
        if (reviews == null || reviews.isEmpty()) {
            return 0; // If no reviews, assume a rating of 0
        }
        int totalRating = reviews.stream().mapToInt(BookReviewDto::getRating).sum();
        return totalRating / reviews.size();
    }

    public List<Book> searchBookByName(String searchStr)
    {
        return bookRepository.findByNameContainingIgnoreCase(searchStr);
    }
    
    @Transactional
    public ResponseEntity<String> postReview(PostReviewDto review)
    {
        Optional<User> optionalUser=userRepository.findById(review.getUserId());
        if (optionalUser.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

        Optional<Book> optionalBook=bookRepository.findById(review.getBookId());
        if (optionalBook.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        
        User user= optionalUser.get();
        Book book= optionalBook.get();

        reviewRepository.save(new Review(user, book, review.getRating(), review.getText()));
        bookRepository.updateUserGenre(user.getId(), book.getId(), review.getRating());
        return ResponseEntity.ok("Review posted successfully");
    }

}
