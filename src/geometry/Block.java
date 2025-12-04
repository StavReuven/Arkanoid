package geometry;
// 322613720 Stav Reuven
import arkanoid.Game;
import arkanoid.Sprite;
import biuoop.DrawSurface;
import listener.HitListener;
import listener.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Block class represents a block in a game. It implements both the Collidable and Sprite interfaces.
 * A block has a rectangle shape defined by its position, width, and height, and a color for its appearance.
 * It can detect collisions with other objects and draw itself on a DrawSurface.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructs a block with the specified rectangle shape and color.
     *
     * @param rectangle the rectangle shape of the block
     * @param color     the color of the block
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Returns the rectangle shape of this block.
     *
     * @return the rectangle shape of this block
     */
    public Rectangle getBlock() {
        return rectangle;
    }

    /**
     * Sets the rectangle shape of this block.
     *
     * @param rectangle the new rectangle shape to set
     */
    public void setBlock(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (!ballColorMatch(hitter) && this.color != Color.LIGHT_GRAY) {
            this.notifyHit(hitter);
            hitter.setColor(this.color);
        }
        if (collisionPoint.getX() == rectangle.getLowerLeft().getX()
                || collisionPoint.getX() == rectangle.getUpperRight().getX()) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        if (collisionPoint.getY() == rectangle.getUpperLeft().getY()
                || collisionPoint.getY() == rectangle.getLowerRight().getY()) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
    }

    @Override
    public void drawOn(DrawSurface d) {
        int x = (int) this.rectangle.getUpperLeft().getX();
        int y = (int) this.rectangle.getUpperLeft().getY();
        int width = (int) this.rectangle.getWidth() - 1;
        int height = (int) this.rectangle.getHeight() - 1;

        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, width, height);
        d.setColor(this.color);
        d.fillRectangle(x, y, width, height);
    }

    @Override
    public void timePassed() {
        // Block does not change over time in this implementation
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Checks if the ball's color matches the block's color.
     *
     * @param ball the ball to check
     * @return true if the ball's color matches the block's color, false otherwise
     */
    public boolean ballColorMatch(Ball ball) {
        return this.color == ball.getColor();
    }

    /**
     * Removes the block from the game.
     *
     * @param g the game to remove the block from
     */
    public void removeFromGame(Game g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifies all registered HitListeners about a hit event.
     *
     * @param hitter the ball that hit the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}

