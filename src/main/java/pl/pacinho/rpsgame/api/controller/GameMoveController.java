package pl.pacinho.rpsgame.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import pl.pacinho.rpsgame.model.MoveDto;
import pl.pacinho.rpsgame.service.GameService;

@Controller
public class GameMoveController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private GameService gameService;

    @MessageMapping("/private")
    public void sendToSpecificUser(@Payload MoveDto moveDto, Authentication authentication) {
        gameService.joinGame(authentication.getName(), moveDto.getMove(), moveDto.getGameId());
        simpMessagingTemplate.convertAndSend("/game/" + moveDto.getGameId(), moveDto);
    }
}
