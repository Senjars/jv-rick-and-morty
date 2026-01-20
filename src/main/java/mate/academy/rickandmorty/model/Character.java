package mate.academy.rickandmorty.model;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "characters")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long externalId;
    @NonNull
    private String name;
    @NonNull
    private String status;
    private String species;
    private String type;
    @NonNull
    private String gender;
    private String origin;
    private String location;
    private String image;
    private List<String> episode;
    private String url;
    private String created;

}
