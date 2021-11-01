package prepelis.catalog.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "title")
    private String title;

    @Enumerated (EnumType.STRING)
    @NotNull
    @Column(name = "genre")
    private Genre genre;

    @NotEmpty
    @Column (name = "releaseDate", columnDefinition = "DATE")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date releaseDate;

    @NotNull
    @Column (name = "duration", columnDefinition = "TIME")
    @JsonFormat(pattern="HH:mm:ss")
    private Time duration;

    @NotBlank
    @Column(name = "trailer")
    private String trailer;

    @Enumerated (EnumType.STRING)
    @NotNull
    @Column(name = "language")
    private Language language;

    @Enumerated (EnumType.STRING)
    @NotNull
    @Column(name = "subtitle")
    private Language subtitle;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "movie_actor", joinColumns = { @JoinColumn(name = "movie_id") }, inverseJoinColumns = {
            @JoinColumn(name = "actor_id") })
    private Set<Actor> actors;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    public void addActor(Actor actor) {
        this.actors.add(actor);
        actor.getMovies().add(this);
    }

    public void removeActor(Actor actor) {
        this.getActors().remove(actor);
        actor.getMovies().remove(this);
    }

    public void removeActors() {
        for (Actor actor : new HashSet<>(actors)) {
            removeActor(actor);
        }
    }

    public void addDirector(Director director) {
        this.director = director;
        director.getMovies().add(this);
    }

    public void removeDirector(Director director) {
        this.getActors().remove(director);
        director.getMovies().remove(this);
    }
}

