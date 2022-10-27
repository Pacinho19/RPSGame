package pl.pacinho.rpsgame.model;

import lombok.Getter;
import lombok.Setter;
import pl.pacinho.rpsgame.model.enums.GameStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Game {

    private String id;
    private GameStatus status;
    private Player player1;
    @Setter
    private Player player2;
    private LocalDateTime startTime;

    public Game(Player player1) {
        this.player1 = player1;
        this.id = UUID.randomUUID().toString();
        this.status = GameStatus.NEW;
        this.startTime = LocalDateTime.now();
    }
}
