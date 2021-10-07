package prepelis.app.dto;

import prepelis.app.model.Genre;
import prepelis.app.model.Language;

import java.sql.Time;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDto {

    private Long id;

    @JsonProperty("Title")
    private String title;
    @JsonProperty("Trailer")
    private String trailer;
    @JsonProperty("Language")
    private Language language;
    @JsonProperty("Subtitle")
    private Language subtitle;
    @JsonProperty("Genre")
    private Genre genre;
    @JsonProperty("Duration")
    private Time duration;
    @JsonProperty("Release Date")
    private Date releaseDate;
    @JsonProperty("Cast")
    private Set<String> actors = new HashSet<>();
    @JsonProperty("Director")
    private Set<String> director = new HashSet<>();

}
