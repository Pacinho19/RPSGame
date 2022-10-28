package pl.pacinho.rpsgame.ui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping(UIConfig.JOIN_TO_GAME)
    public String joinGame(@PathVariable(value = "gameId") String gameId, Authentication authentication, Model model) {
        try {
            if(gameService.checkOpenGame(gameId, authentication.getName()))
                return "redirect:" + UIConfig.GAMES + "/" +gameId;

            gameService.joinGame(authentication.getName(), gameId);
            model.addAttribute("gameId", gameId);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return gameHome(model);
        }
        return "join-game";
    }

    @PostMapping(UIConfig.NEW_GAME)
    public String newGame(Authentication authentication) {
        return "redirect:" + UIConfig.GAMES + "/" + gameService.newGame(authentication.getName());
    }

    @PostMapping(UIConfig.PLAYER_MOVE)
    public String playerMove(@PathVariable(value = "gameId") String gameId, Model model, Authentication authentication) {
        try {
            model.addAttribute("playersProperties", gameService.getPlayerPanelProperties(authentication.getName(), gameId));
            model.addAttribute("endGameInfo", gameService.checkEndGame(gameId));
            model.addAttribute("moves", Move.values());
            model.addAttribute("gameId", gameId);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return gameHome(model);
        }
        return "player-move :: playerMoveFrag";
    }

}