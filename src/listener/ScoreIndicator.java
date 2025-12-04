package listener;

// 322613720 Stav Reuven
import arkanoid.Game;
import arkanoid.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
/**
 * The ScoreIndicator class implements the Sprite interface to display the current score
 * on the screen during the game.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    /**
     * Constructs a ScoreIndicator with the given score counter.
     *
     * @param score the Counter object representing the score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(350, 15, "Score: " + this.score.getValue(), 15);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
