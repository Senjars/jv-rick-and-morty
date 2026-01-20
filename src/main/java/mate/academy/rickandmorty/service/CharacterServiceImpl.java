package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.RickAndMortyResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final Random random = new Random();

    @Override
    public void saveAll(List<CharacterDto> characterDtoList) {

        List<Character> characterModels =
                characterDtoList.stream()
                .map(characterMapper::toModel)
                        .toList();

        characterRepository.saveAll(characterModels);
    }

    @Override
    public void syncCharacters() {
        String url = "https://rickandmortyapi.com/api/character";

        try {
            while (url != null) {
                RickAndMortyResponseDto data =
                        restTemplate.getForObject(url, RickAndMortyResponseDto.class);

                if (data == null || data.getResults() == null) {
                    break;
                }

                List<Character> characters = data.getResults().stream()
                        .map(characterMapper::toModel)
                        .toList();
                characterRepository.saveAll(characters);

                url = (data.getInfo() != null) ? data.getInfo().getNext() : null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Couldn't download character database");
        }
    }

    @Override
    public Page<CharacterDto> searchCharacter(String name, Pageable pageable) {
        return characterRepository.searchCharacterByNameContaining(name, pageable)
                .map(characterMapper::toDto);
    }

    @Override
    public CharacterDto getRandom() {
        long numberOfRecords = characterRepository.count();

        if (numberOfRecords == 0) {
            throw new EntityNotFoundException("No characters in the database");
        }

        int randomIndex = random.nextInt((int) numberOfRecords);
        Page<Character> characterPage = characterRepository
                .findAll(PageRequest.of(randomIndex, 1));

        return characterPage.stream()
                .findFirst()
                .map(characterMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("No characters in the database"));
    }

}
