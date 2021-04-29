package pl.jfxlux.luxbackend.autor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController

public class AutorController {
    private AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @PostMapping("list-of-autors")
    @ResponseStatus(HttpStatus.CREATED)
    public AutorDto addNewAutor(@RequestBody AutorDto dto) {
        return AutorDto.of(autorRepository.save(Autor.of(dto)));

    }

    @PostMapping("edit-autors")
    public void saveEditedAutor(@RequestBody AutorDto autorDto) {
        Autor autor = autorRepository.findById(autorDto.getIdAutor()).get();
        autor.setFirstName(autorDto.getFirstName());
        autor.setLastName(autorDto.getLastName());
        autorRepository.save(autor);
    }

    @GetMapping("list-of-autors")
    public List<AutorDto> getAllAutors() {
        return autorRepository.findAll().stream().map(AutorDto::of).collect(Collectors.toList());
    }

    @GetMapping("list-of-autors/{id}")
    public AutorDto getAutorById(@PathVariable long id) {
        return AutorDto.of(autorRepository.findById(id).get());
    }

    @DeleteMapping("list-of-autors/{id}")
    public void deleteAutorById(@PathVariable long id) {
        autorRepository.deleteById(id);

    }

    @GetMapping("seeking/{text}")
    public List<AutorDto> findAutor(@PathVariable String text) {

        List<Autor> all = autorRepository.findAll();

        String seekText = text.toLowerCase();
        if (seekText == null || seekText.isEmpty()) {
            return all.stream().map(AutorDto::of).collect(Collectors.toList());
        }
        List<Autor> autors = all.stream()
                .filter(autor -> autor.getFirstName().toLowerCase().contains(seekText)
                        || autor.getLastName().toLowerCase().contains(seekText)).collect(Collectors.toList());
        if (autors != null){
            return autors.stream().map(AutorDto::of).collect(Collectors.toList());
        }else {
            return all.stream().map(AutorDto::of).collect(Collectors.toList());
        }


    }


}
