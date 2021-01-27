package pl.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.model.Meme;

@Data
@AllArgsConstructor

public class CardRowFront {
    private Meme meme1;
    private Meme meme2;
    private Meme meme3;
}
