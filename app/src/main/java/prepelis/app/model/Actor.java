package prepelis.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "actors")
    @JsonIgnore
    private Set<Movie> movies;

    public void removeMovie(Movie movie) {
        this.getMovies().remove(movie);
        movie.getActors().remove(this);
    }

    public void removeMovies() {
        for (Movie movie : new HashSet<>(movies)) {
            removeMovie(movie);
        }
    }
}
