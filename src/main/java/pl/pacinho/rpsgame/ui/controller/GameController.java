package pl.pacinho.rpsgame.ui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.pacinho.rpsgame.ui.UIConfig;

@RequiredArgsConstructor
@Controller
public class GameController {

    @GetMapping(UIConfig.GAME_HOME)
    public String gameHome() {
        return "game-home";
    }
}
