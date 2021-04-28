package pl.jfxlux.luxbackend.movie;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String productionYear;
    private String duration;


    public static Movie of(MovieDto newMovie) {
        Movie movie = new Movie();
        movie.setId(newMovie.getIdMovie());
        movie.setTitle(newMovie.getTitle());
        movie.setProductionYear(newMovie.getProductionYear());
        movie.setDuration(newMovie.getDuration());
        return movie;
    }
}
