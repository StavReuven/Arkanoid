package geometry;
// 322613720 Stav Reuven

import arkanoid.Game;
import arkanoid.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;


/**
 * The Paddle class represents a player-controlled paddle in the game. It can move
 * horizontally based on user input, detect collisions with the ball, and draw itself
 * on a DrawSurface.
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Color color;
    private int velocity;

    /**
     * Constructs a new Paddle with the specified rectangle, color, velocity, and keyboard sensor.
     *
     * @param rectangle the rectangle representing the paddle's position and size
     * @param color     the color of the paddle
     * @param velocity  the movement speed of the paddle
     * @param keyboard  the keyboard sensor for user input
     */
    public Paddle(Rectangle rectangle, Color color, int velocity, KeyboardSensor keyboard) {
        this.rectangle = rectangle;
        this.color = color;
        this.velocity = velocity;
        this.keyboard = keyboard;
    }

    /**
     * Moves the paddle to the left by the defined velocity, ensuring it stays within the game bounds.
     */
    public void moveLeft() {
        if (this.rectangle.getUpperLeft().getX() > 20) {
            this.rectangle = new Rectangle(new Point(this.rectangle.getUpperLeft().getX() - this.velocity,
                    this.rectangle.getUpperLeft().getY()), this.rectangle.getWidth(), this.rectangle.getHeight());
        } else {
            this.rectangle = new Rectangle((new Point(680, 560)),
                    this.rectangle.getWidth(), this.rectangle.getHeight());
        }
    }

    /**
     * Moves the paddle to the right by the defined velocity, ensuring it stays within the game bounds.
     */
    public void moveRight() {
        if (rectangle.getUpperRight().getX() < 780) {
            this.rectangle = new Rectangle(new Point(this.rectangle.getUpperLeft().getX() + this.velocity,
                    this.rectangle.getUpperLeft().getY()), this.rectangle.getWidth(), this.rectangle.getHeight());
        } else {
            this.rectangle = new Rectangle((new Point(20, 560)),
                    this.rectangle.getWidth(), this.rectangle.getHeight());
        }
    }


    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }
    @Override
    public void drawOn(DrawSurface d) {
        int x = (int) this.rectangle.getUpperLeft().getX();
        int y = (int) this.rectangle.getUpperLeft().getY();
        int width = (int) this.rectangle.getWidth();
        int height = (int) this.rectangle.getHeight();

        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, width, height);
        d.setColor(color);
        d.fillRectangle(x, y, width, height);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }


    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double regionWidth = this.rectangle.getWidth() / 5;
        double hitX = collisionPoint.getX() - this.rectangle.getUpperLeft().getX();
        double speed = currentVelocity.getVelocity();

        // Check for side collisions
        if (collisionPoint.getX() == rectangle.getLowerLeft().getX()
                || collisionPoint.getX() == rectangle.getUpperRight().getX()) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }

        // Check for top or bottom collisions
        if (collisionPoint.getY() == rectangle.getUpperLeft().getY()
                || collisionPoint.getY() == rectangle.getLowerRight().getY()) {
            if (hitX < regionWidth) {
                return Velocity.fromAngleAndSpeed(210, speed);
            } else if (hitX < 2 * regionWidth) {
                return Velocity.fromAngleAndSpeed(240, speed);
            } else if (hitX < 3 * regionWidth) {
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            } else if (hitX < 4 * regionWidth) {
                return Velocity.fromAngleAndSpeed(300, speed);
            } else {
                return Velocity.fromAngleAndSpeed(330, speed);
            }
        }

        // Default case: return opposite velocity
        return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
    }

    /**
     * Adds the paddle to the game, adding it as both a sprite and a collidable object.
     *
     * @param g the game to which the paddle is added
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
