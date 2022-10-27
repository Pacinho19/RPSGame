package pl.pacinho.rpsgame.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pacinho.rpsgame.exception.GameNotFoundException;
import pl.pacinho.rpsgame.model.Game;
import pl.pacinho.rpsgame.model.MoveDto;
import pl.pacinho.rpsgame.model.Player;
import pl.pacinho.rpsgame.model.PlayerPropertiesDto;
import pl.pacinho.rpsgame.model.enums.GameStatus;
import pl.pacinho.rpsgame.repository.GameRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;

    public List<Game> getAvailableGames() {
        return gameRepository.getAvailableGames();
    }

    public void joinGame(String playerName, String gameId) {
        Game game = gameRepository.joinGame(playerName, gameId);
        game.setStatus(GameStatus.IN_PROGRESS);
    }

    public String newGame(String playerName) {
        return gameRepository.newGame(playerName);
    }

    public Game findById(String gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException(gameId));
    }

    public void playerMove(String name, MoveDto move) {
        checkFinishGame(gameRepository.move(name, move));
    }

    private void checkFinishGame(Game game) {
        if (game.getPlayers().stream().allMatch(p -> p.getMove() != null))
            game.setStatus(GameStatus.FINISHED);
    }

    public List<PlayerPropertiesDto> getPlayerPanelProperties(String name, String gameId) {
        Game game = findById(gameId);
        return game.getPlayers()
                .stream()
                .map(p -> buildPlayerProperties(p, name, game))
                .toList();
    }

    private PlayerPropertiesDto buildPlayerProperties(Player p, String name, Game game) {
        PlayerPropertiesDto playerPropertiesDto = PlayerPropertiesDto.builder()
                .canMove(checkPlayerName(p.getName(), name))
                .showMove(game.getStatus() == GameStatus.FINISHED || checkShowPlayerMove(p, name))
                .ready(p.getMove()!=null)
                .name(p.getName())
                .build();
        playerPropertiesDto.setMove(playerPropertiesDto.isShowMove() ? p.getMove() : null);
        return playerPropertiesDto;
    }

    private boolean checkShowPlayerMove(Player p, String name) {
        return p.getMove() != null && checkPlayerName(p.getName(), name);
    }

    private boolean checkPlayerName(String player, String name) {
        if (player == null) return false;
        return player.equals(name);
    }
}