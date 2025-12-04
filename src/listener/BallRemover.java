package listener;
// 322613720 Stav Reuven

import arkanoid.Game;
import geometry.Ball;
import geometry.Block;

/**
 * The BallRemover class is responsible for removing balls from the game when they are hit.
 * It implements the HitListener interface and is used to keep track of the number of balls
 * remaining in the game.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructs a BallRemover with the specified game and counter for remaining balls.
     *
     * @param game the game from which balls will be removed
     * @param remainingBalls the counter that keeps track of the number of remaining balls
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingBalls.decrease(1);
        hitter.removeFromGame(game);
    }
}

