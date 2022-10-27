package pl.pacinho.rpsgame.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pacinho.rpsgame.exception.GameNotFoundException;
import pl.pacinho.rpsgame.model.Game;
import pl.pacinho.rpsgame.model.Player;
import pl.pacinho.rpsgame.model.enums.Move;
import pl.pacinho.rpsgame.repository.GameRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;

    public List<Game> getAvailableGames(){
        return gameRepository.getAvailableGames();
    }

    public void joinGame(String playerName, Move move, String gameId) {
        gameRepository.joinGame(new Player(playerName, move), gameId);
    }

    public String newGame(String playerName, Move move) {
        return gameRepository.newGame(new Player(playerName, move));
    }

    public Game findById(String gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException(gameId));
    }
}
