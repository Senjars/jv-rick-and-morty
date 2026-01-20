package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterExternalDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    CharacterDto toDto(Character character);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalId", ignore = true)
    Character toModel(CharacterDto characterDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "id", target = "externalId")
    Character toModel(CharacterExternalDto characterExternalDto);
}
