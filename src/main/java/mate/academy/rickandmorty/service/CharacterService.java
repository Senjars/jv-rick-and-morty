package mate.academy.rickandmorty.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import mate.academy.rickandmorty.dto.CharacterDto;

@Service
public interface CharacterService {

    Page<CharacterDto> searchCharacter(String name, Pageable pageable);

    CharacterDto getRandom();

    void syncCharacters();
}
