package pl.pacinho.rpsgame.repository;

import org.springframework.stereotype.Repository;
import pl.pacinho.rpsgame.exception.GameNotFoundException;
import pl.pacinho.rpsgame.model.Game;
import pl.pacinho.rpsgame.model.Player;
import pl.pacinho.rpsgame.model.enums.GameStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class GameRepository {

    private Map<String, Game> gameMap;

    public GameRepository() {
        gameMap = new HashMap<>();
    }

    public String newGame(Player player) {
        Game game = new Game(player);
        gameMap.put(game.getId(), game);
        return game.getId();
    }

    public void joinGame(Player player, String gameId) {
        Game game = gameMap.get(gameId);
        if (game == null)
            throw new GameNotFoundException(gameId);

        if (game.getStatus() != GameStatus.NEW)
            throw new IllegalStateException("Cannot join to " + gameId + ". Game status : " + game.getStatus());

        if (game.getPlayer1().getName().equals(player.getName()))
            throw new IllegalStateException("Game " + gameId + " was created by you!");

        game.setPlayer2(player);
    }

    public List<Game> getAvailableGames() {
        return gameMap.values()
                .stream()
                .filter(game -> game.getStatus() == GameStatus.NEW)
                .toList();
    }

    public Optional<Game> findById(String gameId) {
        return Optional.ofNullable(gameMap.get(gameId));
    }
}
