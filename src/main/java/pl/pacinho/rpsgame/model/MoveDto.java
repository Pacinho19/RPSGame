package pl.pacinho.rpsgame.model;

import lombok.Getter;
import pl.pacinho.rpsgame.model.enums.Move;

@Getter
public class MoveDto {

    private int player;
    private String gameId;
    private Move move;
}