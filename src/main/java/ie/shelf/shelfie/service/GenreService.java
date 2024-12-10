package ie.shelf.shelfie;
/*
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//import java.util.Optional;

@Service
public class GenreService {
    
    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getGenreAll()
    {
        return genreRepository.findAll();
    }
}
