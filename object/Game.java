package object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    UUID id;
    Long query;
    Integer stepAmount;
    GameType gameType;
    LocalDateTime dateCreation;

}
