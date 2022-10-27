package pl.pacinho.rpsgame.ui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.pacinho.rpsgame.service.GameService;
import pl.pacinho.rpsgame.ui.UIConfig;

@RequiredArgsConstructor
@Controller
public class GameController {

    private final GameService gameService;

    @GetMapping(UIConfig.GAME_HOME)
    public String gameHome() {
        return "game-home";
    }

    @PostMapping(UIConfig.GAMES_LIST)
    public String availableGames(Model model) {
        model.addAttribute("games", gameService.getAvailableGames());
        return "availableGames :: availableGamesFrag";
    }
}
