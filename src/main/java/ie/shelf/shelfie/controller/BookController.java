package ie.shelf.shelfie;

//import ie.shelf.shelfie.dto.UserResponseDto;
//import ie.shelf.shelfie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
