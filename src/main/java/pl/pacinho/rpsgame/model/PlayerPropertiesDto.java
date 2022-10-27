package pl.pacinho.rpsgame.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.pacinho.rpsgame.model.enums.Move;

@AllArgsConstructor
@Builder
@Getter
public class PlayerPropertiesDto {

    private String name;
    @Setter
    private Move move;
    private boolean canMove;
    private boolean showMove;
    private boolean ready;
}