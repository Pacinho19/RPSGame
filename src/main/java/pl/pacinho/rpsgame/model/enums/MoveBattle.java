package pl.pacinho.rpsgame.model.enums;

import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
public enum MoveBattle {

    RP(Move.ROCK, Move.PAPER),
    PS(Move.PAPER, Move.SCISSORS),
    SR(Move.SCISSORS, Move.ROCK);

    private final Move move;
    private final Move winMove;

    public static int checkWin(Move move1, Move move2) {
        return findByMove(move1).winMove == move2 ? 1 : 0;
    }

    private static MoveBattle findByMove(Move move) {
        return Stream.of(MoveBattle.values())
                .filter(m -> m.move == move)
                .findFirst()
                .get();
    }


}
