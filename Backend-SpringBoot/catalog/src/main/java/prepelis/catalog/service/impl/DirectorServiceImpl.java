package prepelis.catalog.service.impl;

import org.springframework.stereotype.Service;
import prepelis.catalog.dto.DirectorDto;
import prepelis.catalog.model.Director;
import prepelis.catalog.model.Movie;
import prepelis.catalog.repository.DirectorRepository;
import prepelis.catalog.repository.MovieRepository;
import prepelis.catalog.service.api.DirectorService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Resource
    private MovieRepository movieRepository;

    @Resource
    private DirectorRepository directorRepository;

    @Override
    public String addDirector(DirectorDto directorDto) {
        if (directorRepository.findByName(directorDto.getName()) == null){
            Director director = new Director();
            mapDtoToEntity(directorDto, director);
            directorRepository.save(director);
            return "Director successfully created";
        } else {
            return "Director name already register";
        }
    }

    @Override
    public DirectorDto getDirectorById(Long directorId){
        Director director = directorRepository.findDirectorById(directorId);
        return mapEntityToDto(director);
    }

    @Override
    public DirectorDto getDirectorByName(String name) {
        Director director = directorRepository.findByName(name);
        return mapEntityToDto(director);
    }

    @Override
    public List<DirectorDto> getAllDirectors() {
        List<DirectorDto> directorDtos = new ArrayList<>();
        List<Director> directors = directorRepository.findAll();
        directors.forEach(director -> {
            DirectorDto directorDto = mapEntityToDto(director);
            directorDtos.add(directorDto);
        });
        return directorDtos;
    }

    @Override
    public String updateDirector(Long directorId, DirectorDto directorDto) {
        Director director = directorRepository.findDirectorById(directorId);
        if (directorRepository.findByName(directorDto.getName()) == null || directorDto.getName().equals(director.getName())) {
            mapDtoToEntity(directorDto, director);
            directorRepository.save(director);
            return "Director successfully edited!";
        } else {
            return "Director name already register";
        }
    }

    @Override
    public String deleteDirector(Long directorId) {
        Optional<Director> director = directorRepository.findById(directorId);
        if(director.isPresent()) {
            directorRepository.deleteById(director.get().getId());
            return "Director with id: " + directorId + " deleted successfully!";
        } else return "Director not found in database";
    }

    private String mapDtoToEntity(DirectorDto directorDto, Director director) {
        director.setName(directorDto.getName());
        return null;
    }

    private DirectorDto mapEntityToDto(Director director) {
        DirectorDto responseDto = new DirectorDto();
        responseDto.setId(director.getId());
        responseDto.setName(director.getName());
        responseDto.setMovies(director.getMovies().stream().map(Movie::getTitle).collect(Collectors.toSet()));
        return responseDto;
    }
}
