package pl.pacinho.rpsgame.repository;

import org.springframework.stereotype.Repository;
import pl.pacinho.rpsgame.exception.GameNotFoundException;
import pl.pacinho.rpsgame.exception.PlayerNotFoundException;
import pl.pacinho.rpsgame.model.Game;
import pl.pacinho.rpsgame.model.MoveDto;
import pl.pacinho.rpsgame.model.Player;
import pl.pacinho.rpsgame.model.enums.GameStatus;

import java.util.*;
import java.util.stream.Stream;

@Repository
public class GameRepository {

    private Map<String, Game> gameMap;

    public GameRepository() {
        gameMap = new HashMap<>();
    }

    public String newGame(String playerName) {
        Game game = new Game(playerName);
        gameMap.put(game.getId(), game);
        return game.getId();
    }

    public Game joinGame(String name, String gameId) {
        Game game = gameMap.get(gameId);
        if (game == null)
            throw new GameNotFoundException(gameId);

        if (game.getStatus() != GameStatus.NEW)
            throw new IllegalStateException("Cannot join to " + gameId + ". Game status : " + game.getStatus());

        if (game.getPlayers().get(0).getName().equals(name))
            throw new IllegalStateException("Game " + gameId + " was created by you!");

        game.getPlayers().stream()
                .filter(p -> p.getName() == null)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Game is in progress!"))
                .setName(name);

        return game;
    }

    public List<Game> getAvailableGames() {
        return gameMap.values()
                .stream()
                .filter(game -> game.getStatus() != GameStatus.FINISHED)
                .toList();
    }

    public Optional<Game> findById(String gameId) {
        return Optional.ofNullable(gameMap.get(gameId));
    }

    public Game move(String name, MoveDto move) {
        Game game = gameMap.get(move.getGameId());
        if (game == null) throw new GameNotFoundException(move.getGameId());

        game.getPlayers()
                .stream()
                .filter(p -> p.getName() != null && p.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new PlayerNotFoundException(name))
                .setMove(move.getMove());

        return game;
    }
}