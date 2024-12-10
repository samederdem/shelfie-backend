package ie.shelf.shelfie;

//import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/all")
    public List<Genre> getGenreAll() {
        return genreService.getGenreAll();
    }
}
