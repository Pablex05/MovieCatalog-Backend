package prepelis.app.service.impl;

import prepelis.app.dto.DirectorDto;
import prepelis.app.model.Director;
import prepelis.app.model.Movie;
import prepelis.app.repository.DirectorRepository;
import prepelis.app.repository.MovieRepository;
import prepelis.app.service.api.DirectorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Resource
    private MovieRepository movieRepository;

    @Resource
    private DirectorRepository directorRepository;

    @Transactional
    @Override
    public DirectorDto addDirector(DirectorDto directorDto) {
        Director director = new Director();
        mapDtoToEntity(directorDto, director);
        Director savedDirector = directorRepository.save(director);
        return mapEntityToDto(savedDirector);
    }

    @Override
    public DirectorDto getDirector(Long id) {
        DirectorDto directorDto = new DirectorDto();
        Director actor = directorRepository.findDirectorById(id);
        try {
            directorDto = mapEntityToDto(actor);
            return directorDto;
        }catch (Exception e){
            e.getCause();
        }
        return null;
    }

    @Override
    public List<DirectorDto> getAllDirectors() {
        List<DirectorDto> directorsDto = new ArrayList<>();
        List<Director>  directors = directorRepository.findAll();
        directors.forEach(director -> {
            DirectorDto directorDto = mapEntityToDto(director);
            directorsDto.add(directorDto);
        });
        return directorsDto;
    }

    @Transactional
    @Override
    public DirectorDto updateDirector(Long id, DirectorDto directorDto) {
        Director crs = directorRepository.getOne(id);
        Movie std = movieRepository.getOne(id);
        std.removeDirector(crs);
        mapDtoToEntity(directorDto, crs);
        Director director = directorRepository.save(crs);
        return mapEntityToDto(director);
    }

    @Transactional
    @Override
    public String deleteDirector(Long id) {
        Optional<Director> director = directorRepository.findById(id);
        if(director.isPresent()) {
            director.get().removeMovies();
            directorRepository.deleteById(director.get().getId());
            return "Director with id: " + id + " deleted successfully!";
        }
        return null;
    }

    private void mapDtoToEntity(DirectorDto directorDto, Director director) {
        director.setName(directorDto.getName());
        if (null == director.getMovies()) {
            director.setMovies(new HashSet<>());
        }
        directorDto.getMovies().forEach(movieTitle -> {
            Movie movie = movieRepository.findByTitle(movieTitle);
            if (null == movie) {
                movie = new Movie();
                movie.setActors(new HashSet<>());
            }
            movie.setTitle(movieTitle);
            movie.addDirector(director);
        });
    }

    private DirectorDto mapEntityToDto(Director director) {
        DirectorDto responseDto = new DirectorDto();
        responseDto.setName(director.getName());
        responseDto.setId(director.getId());
        responseDto.setMovies(director.getMovies().stream().map(Movie::getTitle).collect(Collectors.toSet()));
        return responseDto;
    }
}
