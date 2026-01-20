package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "characters", description = "Operations for managing characters")
@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/search")
    @Operation(
            summary = "search for characters",
            description = "returns characters that fulfill the name requirement",
            responses = {
                    @ApiResponse(responseCode = "200",
                                 description = "Characters found successfully")
            }
    )
    public Page<CharacterDto> searchCharacter(@RequestParam String name, Pageable pageable) {
        return characterService.searchCharacter(name, pageable);
    }

    @GetMapping("/random")
    @Operation(
            summary = "receive info about a random character",
            description = "returns info about a randomly chosen character"
    )
    public CharacterDto getRandom() {
        return characterService.getRandom();
    }
}
