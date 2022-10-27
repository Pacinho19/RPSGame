package pl.pacinho.rpsgame.repository;

import org.springframework.stereotype.Repository;
import pl.pacinho.rpsgame.model.Game;
import pl.pacinho.rpsgame.model.enums.GameStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GameRepository {

    private Map<String, Game> gameMap;

    public GameRepository() {
        gameMap = new HashMap<>();
        newGame("Player1234");

        new Thread(() -> {
            try {
                Thread.sleep(10000);
                newGame("Thread Player");
                System.out.println("Added thread player");
            } catch (InterruptedException e) {
            }
        }).start();
    }

    public String newGame(String playerName) {
        Game game = new Game(playerName);
        gameMap.put(game.getId(), game);
        return game.getId();
    }

    public void joinGame(String playerName, String gameId) {
        Game game = gameMap.get(gameId);
        if (game == null)
            throw new IllegalArgumentException("Game " + gameId + " not found !");

        if (game.getStatus() != GameStatus.NEW)
            throw new IllegalStateException("Cannot join to " + gameId + ". Game status : " + game.getStatus());

        if (game.getPlayer1().equals(playerName))
            throw new IllegalStateException("Game " + gameId + " was created by you!");

        game.setPlayer2(playerName);
    }

    public List<Game> getAvailableGames() {
        return gameMap.values()
                .stream()
                .filter(game -> game.getStatus() == GameStatus.NEW)
                .toList();
    }
}
