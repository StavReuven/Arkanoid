package listener;

// 322613720 Stav Reuven

import arkanoid.Game;
import geometry.Ball;
import geometry.Block;

/**
 * BlockRemover is responsible for removing blocks from the game and updating the count of remaining blocks.
 * It implements HitListener to handle hit events on blocks.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructor for BlockRemover.
     *
     * @param game the game instance where blocks are removed
     * @param remainingBlocks counter for tracking remaining blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingBlocks.decrease(1);
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(game);
    }
}

