package pl.pacinho.rpsgame.ui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.pacinho.rpsgame.model.enums.Move;
import pl.pacinho.rpsgame.service.GameService;
import pl.pacinho.rpsgame.ui.UIConfig;

@RequiredArgsConstructor
@Controller
public class GameController {

    private final GameService gameService;

    @GetMapping(UIConfig.HOME)
    public String gameHome(Model model) {
        return "home";
    }

    @GetMapping(UIConfig.GAME_PAGE)
    public String gamePage(@PathVariable(value = "gameId") String gameId, Model model) {
        try {
            model.addAttribute("game", gameService.findById(gameId));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return gameHome(model);
        }
        return "game";
    }

    @PostMapping(UIConfig.GAMES)
    public String availableGames(Model model) {
        model.addAttribute("games", gameService.getAvailableGames());
        return "availableGames :: availableGamesFrag";
    }

    @GetMapping(UIConfig.JOIN_TO_GAME)
    public String joinGamePage(@PathVariable(value = "gameId") String gameId, Model model, Authentication authentication) {
        model.addAttribute("gameId", gameId);
        model.addAttribute("playerName", authentication.getName());
        model.addAttribute("moves", Move.values());
        return "join-game";
    }

    @GetMapping(UIConfig.NEW_GAME)
    public String newGamePage(Model model) {
        model.addAttribute("moves", Move.values());
        return "new-game";
    }
    @PostMapping(UIConfig.NEW_GAME)
    public String newGame(@RequestParam Move move, Authentication authentication) {
        return "redirect:" + UIConfig.GAMES + "/" + gameService.newGame(authentication.getName(), move);
    }
}
