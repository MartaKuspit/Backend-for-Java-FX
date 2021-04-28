package pl.jfxlux.luxbackend.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.jfxlux.luxbackend.autor.Autor;
import pl.jfxlux.luxbackend.autor.AutorDto;
import pl.jfxlux.luxbackend.autor.AutorRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MovieController {
    private MovieRepository movieRepository;
    private AutorRepository autorRepository;

    public MovieController(MovieRepository movieRepository, AutorRepository autorRepository) {
        this.movieRepository = movieRepository;
        this.autorRepository = autorRepository;
    }

    @PostMapping("movie-create")
    public Movie addNewMovie(@RequestBody Movie movie){
        return movieRepository.save(movie);
    }
    @GetMapping("list-of-movies/{idAutor}")
    public List<MovieDto> getAllAutorMovies(@PathVariable long idAutor){
        AutorDto autorDto = AutorDto.of(autorRepository.findById(idAutor).get());
        List<MovieDto> movieList = autorDto.getMovieList().stream().map(MovieDto::of).collect(Collectors.toList());
        return movieList;

    }
    @PostMapping("list-of-movies/{idAutor}")
    public void addNewMovieToAutor(@PathVariable long idAutor, @RequestBody MovieDto movieDto){
        movieRepository.save(Movie.of(movieDto));
        Autor autor = autorRepository.findById(idAutor).get();
        autor.getMovieList().add(Movie.of(movieDto));
        autorRepository.save(autor);
    }
    @PostMapping("list-of-movies")
    public void addNewMovieToAutor( @RequestBody MovieDto movieDto){
        MovieDto.of(movieRepository.save(Movie.of(movieDto)));


    }
    @PutMapping("list-of-movies")
    public void saveEditedMovie(@RequestBody MovieDto movieDto){
        MovieDto.of(movieRepository.save(Movie.of(movieDto)));
    }


}
