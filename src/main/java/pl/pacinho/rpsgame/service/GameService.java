package pl.pacinho.rpsgame.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pacinho.rpsgame.model.Game;
import pl.pacinho.rpsgame.repository.GameRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;

    public List<Game> getAvailableGames(){
        return gameRepository.getAvailableGames();
    }

}
