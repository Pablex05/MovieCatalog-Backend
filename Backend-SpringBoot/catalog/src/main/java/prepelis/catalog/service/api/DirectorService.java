package prepelis.catalog.service.api;

import prepelis.catalog.dto.DirectorDto;
import java.util.List;

public interface DirectorService {

    public String addDirector(DirectorDto directorDto);

    public DirectorDto getDirectorById(Long directorId);

    public DirectorDto getDirectorByName(String name);

    public List<DirectorDto> getAllDirectors();

    public String updateDirector(Long directorId, DirectorDto directorDto);

    public String deleteDirector(Long directorId);

}
