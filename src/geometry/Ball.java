package geometry;
// 322613720 Stav Reuven
import arkanoid.Game;
import arkanoid.GameEnvironment;
import arkanoid.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The Ball class represents a ball with a center point, radius, color, and velocity.
 * It can move, draw itself on a DrawSurface, and detect collisions with a frame.
 */
public class Ball implements Sprite {
    private final int radius;
    private Point center;
    private Color color;
    private Velocity v;
    private GameEnvironment gameEnvironment;
    private double epsilon = 0.001;

    /**
     * Constructs a ball with the specified radius, center point, color, and game environment.
     *
     * @param radius          the radius of the ball
     * @param center          the center point of the ball
     * @param color           the color of the ball
     * @param gameEnvironment the game environment containing collidables
     */
    public Ball(int radius, Point center, Color color, GameEnvironment gameEnvironment) {
        this.radius = radius;
        this.center = center;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Draws the ball on a DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillCircle((int) center.getX(), (int) center.getY(), radius);
    }

    /**
     * Moves the ball one step according to its velocity.
     * If the ball collides with a collidable object, updates its position and velocity accordingly.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Adds the ball to the game by adding it as a sprite.
     *
     * @param g the game to add the ball to
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Moves the ball one step according to its current velocity.
     * Handles collisions with collidable objects in the game environment.
     * Adjusts position and velocity of the ball after collision.
     */
    public void moveOneStep() {
        Point end = new Point(this.center.getX() + this.v.getDx(), this.center.getY() + this.v.getDy());
        Line trajectory = new Line(this.center, end);

        // Check for collisions with the game environment
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo == null) {
            this.center = this.v.applyToPoint(this.center);
        } else {
            Point collisionPoint = collisionInfo.collisionPoint();
            Collidable collidable = collisionInfo.collisionObject();

            // Adjust position slightly before the collision point to avoid sticking
            this.center = new Point(collisionPoint.getX() - this.v.getDx() * epsilon,
                    collisionPoint.getY() - this.v.getDy() * epsilon);

            // Handle the collision and update the velocity
            this.v = collidable.hit(this, collisionPoint, this.v);
        }
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the new velocity to set
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Returns the size (radius) of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets the color of the ball.
     *
     * @param color the new color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Removes the ball from the game.
     *
     * @param g the game to remove the ball from
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }
}

