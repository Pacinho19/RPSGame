package pl.pacinho.rpsgame.model;

import lombok.Getter;
import lombok.Setter;
import pl.pacinho.rpsgame.model.enums.GameStatus;

import java.util.UUID;

@Getter
public class Game {

    private String id;
    private GameStatus status;
    private String player1;

    @Setter
    private String player2;

    public Game(String player1) {
        this.player1 = player1;
        this.id = UUID.randomUUID().toString();
        this.status = GameStatus.NEW;
    }
}
