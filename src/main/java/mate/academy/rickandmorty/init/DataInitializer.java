package mate.academy.rickandmorty.init;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final CharacterService characterService;

    @Override
    public void run(String... args) throws Exception {
        characterService.syncCharacters();
    }
}
