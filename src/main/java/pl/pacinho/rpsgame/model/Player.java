package pl.pacinho.rpsgame.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
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