package listener;

// 322613720 Stav Reuven

import geometry.Ball;
import geometry.Block;

/**
 * The HitListener interface represents an object that listens for hit events.
 * It provides a method that is called whenever a specified object is hit.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the block that is being hit
     * @param hitter the ball that is doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}


