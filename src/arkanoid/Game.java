package arkanoid;
// 322613720 Stav Reuven
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import geometry.Point;
import geometry.Paddle;
import geometry.Block;
import geometry.Collidable;
import geometry.Rectangle;
import geometry.Ball;
import geometry.Velocity;
import listener.BallRemover;
import listener.Counter;
import listener.ScoreIndicator;
import listener.ScoreTrackingListener;
import listener.BlockRemover;

import java.awt.Color;

/**
 * The Game class initializes and manages the game elements such as sprites, collidables,
 * and the graphical user interface (GUI). It sets up the game environment, including walls,
 * blocks, balls, and a paddle, and runs the game animation loop.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter scoreCounter;

    /**
     * Constructs a new Game object with default dimensions and initializes the sprites,
     * game environment, and GUI components.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("Arkanoid", 800, 600);
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.scoreCounter = new Counter();
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a sprite object to the game sprites collection.
     *
     * @param s the sprite object to add
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initializes the game by creating walls, blocks, balls, paddle, and score indicator,
     * and adds them to the game.
     */
    public void initialize() {
        // Creating and adding walls
        Block wallLeft = new Block(new Rectangle(new Point(0, 0), 20, 600), Color.lightGray);
        wallLeft.addToGame(this);
        Block wallTop = new Block(new Rectangle(new Point(0, 0), 800, 20), Color.lightGray);
        wallTop.addToGame(this);
        Block wallRight = new Block(new Rectangle(new Point(780, 0), 20, 600), Color.lightGray);
        wallRight.addToGame(this);
        Block deathRegion = new Block(new Rectangle(new Point(0, 580), 800, 20), Color.white);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(new BallRemover(this, remainingBalls));

        // Creating and adding balls
        Ball ball1 = new Ball(5, new Point(420, 30), Color.BLACK, environment);
        ball1.setVelocity(Velocity.fromAngleAndSpeed(240, 5));
        ball1.addToGame(this);
        this.remainingBalls.increase(1);

        Ball ball2 = new Ball(5, new Point(123, 35), Color.BLUE, environment);
        ball2.setVelocity(Velocity.fromAngleAndSpeed(324, 5));
        ball2.addToGame(this);
        this.remainingBalls.increase(1);

        Ball ball3 = new Ball(5, new Point(152, 45), Color.BLUE, environment);
        ball3.setVelocity(Velocity.fromAngleAndSpeed(25, 5));
        ball3.addToGame(this);
        this.remainingBalls.increase(1);

        // Creating and adding paddle
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        Paddle paddle = new Paddle(new Rectangle(new Point(350, 560), 100, 20), Color.ORANGE, 8, keyboard);
        paddle.addToGame(this);

        // Creating and adding score indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.scoreCounter);
        scoreIndicator.addToGame(this);

        // Creating and adding blocks
        addBlocks();
    }

    /**
     * Helper method to add blocks of different colors to the game.
     */
    private void addBlocks() {
        ScoreTrackingListener score = new ScoreTrackingListener(scoreCounter);
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        int startX = 169;
        int startY = 100;
        int blockWidth = 51;
        int blockHeight = 20;
        Color[] blockColors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.PINK, Color.GREEN};

        for (int row = 0; row < blockColors.length; row++) {
            for (int col = row; col < 12; col++) {
                Block block = new Block(new Rectangle(new Point(startX + col * blockWidth, startY + row * blockHeight),
                        blockWidth, blockHeight), blockColors[row]);
                block.addToGame(this);
                block.addHitListener(blockRemover);
                block.addHitListener(score);
                this.remainingBlocks.increase(1);
            }
        }
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.remove(c);
    }

    /**
     * Removes a sprite object from the game sprites collection.
     *
     * @param s the sprite object to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Runs the game animation loop, continuously updating and drawing the game components
     * until either all blocks are destroyed or all balls are lost.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (this.remainingBlocks.getValue() > 0 && this.remainingBalls.getValue() > 0) {
            if (this.remainingBlocks.getValue() == 0) {
                this.scoreCounter.increase(100);
            }
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        this.gui.close();
    }
}

