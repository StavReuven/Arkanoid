package arkanoid;

// 322613720 Stav Reuven

import biuoop.DrawSurface;

import java.util.List;
import java.util.ArrayList;

/**
 * The SpriteCollection class represents a collection of sprites.
 * It manages adding sprites, updating their state over time, and drawing them on a DrawSurface.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Constructs a new SpriteCollection with an empty list of sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Notifies all sprites in the collection that a unit of time has passed.
     * Calls the timePassed() method on each sprite.
     */
    public void notifyAllTimePassed() {
        List<Sprite> copy = new ArrayList<>(this.sprites);
        for (Sprite s : copy) {
            s.timePassed();
        }
    }

    /**
     * Draws all sprites in the collection on the given DrawSurface.
     * Calls the drawOn(d) method on each sprite.
     *
     * @param d the DrawSurface on which to draw the sprites
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.sprites) {
            s.drawOn(d);
        }
    }
    /**
     * Removes a sprite from the collection.
     *
     * @param s the sprite to remove
     */
    public void remove(Sprite s) {
        this.sprites.remove(s);
    }
}

