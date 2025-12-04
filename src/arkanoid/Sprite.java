package arkanoid;

// 322613720 Stav Reuven
import biuoop.DrawSurface;

/**
 * The Sprite interface represents objects that can be drawn on a DrawSurface,
 * update their state over time, and can be added to a game.
 */
public interface Sprite {
    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param d the DrawSurface on which the sprite is drawn
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that a unit of time has passed.
     * This method is called repeatedly in a game loop to update the sprite's state.
     */
    void timePassed();

    /**
     * Adds the sprite to the specified game.
     *
     * @param g the game to which the sprite is added
     */
    void addToGame(Game g);
}


