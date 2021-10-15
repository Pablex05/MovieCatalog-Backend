package prepelis.app.model;

import java.sql.Time;
import java.sql.Date;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "title")
    private String title;

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

    @Enumerated (EnumType.STRING)
    @NotNull
    @Column(name = "genre")
    private Genre genre;

    @NotNull
    @Column (name = "duration", columnDefinition = "TIME")
    @JsonFormat(pattern="HH:mm:ss")
    private Time duration;

    @NotNull
    @Column (name = "releaseDate", columnDefinition = "DATE")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date releaseDate;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "movie_actor", joinColumns = { @JoinColumn(name = "movie_id") }, inverseJoinColumns = {
            @JoinColumn(name = "actor_id") })
    private Set<Actor> actors;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "movie_director", joinColumns = { @JoinColumn(name = "movie_id") }, inverseJoinColumns = {
            @JoinColumn(name = "director_id") })
    private Set<Director> director;

    public void addActor(Actor actor) {
        this.actors.add(actor);
        actor.getMovies().add(this);
    }

    public void removeActor(Actor actor) {
        this.getActors().remove(actor);
        actor.getMovies().remove(this);
    }

    public void addDirector(Director director) {
        this.director.add(director);
        director.getMovies().add(this);
    }

    public void removeDirector(Director director) {
        this.getDirector().remove(director);
        director.getMovies().remove(this);
    }
}
