package pl.pacinho.rpsgame.model;

import lombok.Getter;
import lombok.Setter;
import pl.pacinho.rpsgame.model.enums.GameStatus;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Getter
public class Game {

    private String id;
    @Setter
    private GameStatus status;
    private LinkedList<Player> players;
    private LocalDateTime startTime;

    public Game(String player1) {
        players = new LinkedList<>();
        players.add(new Player(player1));
        players.add(new Player());
        this.id = UUID.randomUUID().toString();
        this.status = GameStatus.NEW;
        this.startTime = LocalDateTime.now();
    }

}