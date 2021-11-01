package prepelis.catalog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class DirectorDto {

    private Long id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Movies")
    Set<String> movies = new HashSet<>();

}
