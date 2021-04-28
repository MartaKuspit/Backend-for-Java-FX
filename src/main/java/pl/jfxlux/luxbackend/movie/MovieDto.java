package pl.jfxlux.luxbackend.movie;

import lombok.Data;

@Data
public class MovieDto {
    private Long idMovie;
    private String title;
    private String productionYear;
    private String duration;

    public static MovieDto of(Movie movie){
        MovieDto dto = new MovieDto();
        dto.setIdMovie(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setProductionYear(movie.getProductionYear());
        dto.setDuration(movie.getDuration());
        return dto;

    }
}
