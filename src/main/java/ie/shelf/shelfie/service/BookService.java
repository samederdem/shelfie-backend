package ie.shelf.shelfie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


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
}
