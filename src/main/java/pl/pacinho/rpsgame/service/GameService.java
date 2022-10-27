package pl.pacinho.rpsgame.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pacinho.rpsgame.repository.GameRepository;

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;

}
