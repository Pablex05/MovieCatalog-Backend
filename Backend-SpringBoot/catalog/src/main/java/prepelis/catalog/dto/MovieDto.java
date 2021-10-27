package prepelis.catalog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import prepelis.catalog.model.Genre;
import prepelis.catalog.model.Language;

import java.sql.Time;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class MovieDto {

    private Long id;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Genre")
    private Genre genre;

    @JsonProperty("Release Date")
    private Date releaseDate;

    @JsonProperty("Duration")
    private Time duration;

    @JsonProperty("Trailer")
    private String trailer;

    @JsonProperty("Language")
    private Language language;

    @JsonProperty("Subtitle")
    private Language subtitle;

    @JsonProperty("Cast")
    Set<String> actors = new HashSet<>();

    @JsonProperty("Director")
    String director;

}
