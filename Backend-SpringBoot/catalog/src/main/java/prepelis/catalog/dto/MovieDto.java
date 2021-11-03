package prepelis.catalog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import prepelis.catalog.model.Genre;
import prepelis.catalog.model.Language;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class MovieDto{

    private Long id;

    @NotBlank(message = "Title is required")
    @JsonProperty("Title")
    private String title;

    @NotNull(message = "Genre is required")
    @JsonProperty("Genre")
    private Genre genre;

    @NotNull(message = "Release date is required")
    @JsonProperty("Release Date")
    private Date releaseDate;

    @NotNull(message = "Duration is required")
    @JsonProperty("Duration")
    private Time duration;

    @NotBlank(message = "Trailer is required")
    @JsonProperty("Trailer")
    private String trailer;

    @NotNull(message = "Language is required")
    @JsonProperty("Language")
    private Language language;

    @NotNull(message = "Subtitle is required")
    @JsonProperty("Subtitle")
    private Language subtitle;

    @JsonProperty("Cast")
    Set<String> actors = new HashSet<>();

    @JsonProperty("Director")
    String director;

}
