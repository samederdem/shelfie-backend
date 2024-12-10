package ie.shelf.shelfie;

import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{bookId}")
    public BookDto getBookDetails(@PathVariable Long bookId) {
        return bookService.getBookDetails(bookId);
    }

    @GetMapping("/search/{searchStr}")
    public List<Book> searchBookByName(@PathVariable String searchStr)
    {
        return bookService.searchBookByName(searchStr);
    }

    @PostMapping("/review")
    public ResponseEntity<String> postReview(@RequestBody PostReviewDto review)
    {
        return bookService.postReview(review);
    }
}
