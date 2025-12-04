package listener;

// 322613720 Stav Reuven

import geometry.Ball;
import geometry.Block;

/**
 * The ScoreTrackingListener class implements the HitListener interface to track and update
 * the score whenever a block is hit.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * Constructs a ScoreTrackingListener with the given score counter.
     *
     * @param scoreCounter the Counter object representing the current score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
