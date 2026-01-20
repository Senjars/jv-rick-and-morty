package mate.academy.rickandmorty.dto;

import java.util.List;
import lombok.Data;

@Data
public class RickAndMortyResponseDto {
    private List<CharacterExternalDto> results;
    private InfoDto info;
}
