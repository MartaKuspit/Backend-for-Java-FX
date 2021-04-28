package pl.jfxlux.luxbackend.autor;

import lombok.Data;
import pl.jfxlux.luxbackend.movie.Movie;
import pl.jfxlux.luxbackend.movie.MovieDto;

import java.util.List;

@Data
public class AutorDto {
    private Long idAutor;
    private String firstName;
    private String lastName;
    private List<Movie> movieList;

    public static AutorDto of(Autor autor){
        AutorDto dto = new AutorDto();
        dto.setIdAutor(autor.getId());
        dto.setFirstName(autor.getFirstName());
        dto.setLastName(autor.getLastName());
        dto.setMovieList(autor.getMovieList());
        return dto;
    }
}
