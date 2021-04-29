package pl.jfxlux.luxbackend.autor;

import lombok.Data;
import pl.jfxlux.luxbackend.movie.Movie;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Movie> movieList;

    public static Autor of(AutorDto newAutor){
        Autor autor = new Autor();
        autor.setId(newAutor.getIdAutor());
        autor.setFirstName(newAutor.getFirstName());
        autor.setLastName(newAutor.getLastName());
        autor.setMovieList(newAutor.getMovieList());
        return autor;

    }


}
