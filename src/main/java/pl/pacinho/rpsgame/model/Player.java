package pl.pacinho.rpsgame.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.pacinho.rpsgame.model.enums.Move;

@AllArgsConstructor
@Getter
public class Player {
    private String name;
    private Move move;

}
