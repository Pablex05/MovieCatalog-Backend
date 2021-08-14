package prepelis.app.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "director")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "director")
    @JsonIgnore
    private Set<Movie> movies;

    public void removeMovie(Movie movie) {
        this.getMovies().remove(movie);
        movie.getDirector().remove(this);
    }

    public void removeMovies() {
        for (Movie movie : new HashSet<>(movies)) {
            removeMovie(movie);
        }
    }
}
