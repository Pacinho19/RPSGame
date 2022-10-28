package pl.pacinho.rpsgame.model;

import lombok.Getter;
import lombok.Setter;
import pl.pacinho.rpsgame.model.enums.Move;

@Setter
@Getter
public class Player {
    private String name;
    private Move move;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }
}