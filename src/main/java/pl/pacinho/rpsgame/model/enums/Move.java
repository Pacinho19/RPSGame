package pl.pacinho.rpsgame.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.pacinho.rpsgame.utils.ImageUtils;

@RequiredArgsConstructor
public enum Move {

    ROCK(ImageUtils.getBase64Image("ROCK")),
    PAPER(ImageUtils.getBase64Image("PAPER")),
    SCISSORS(ImageUtils.getBase64Image("SCISSORS"));

    @Getter
    private final String base64Image;

}
