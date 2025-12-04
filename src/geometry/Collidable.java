package geometry;
// 322613720 Stav Reuven
/**
 * The Collidable interface represents objects that can be collided with in a game.
 * It provides methods to retrieve the collision shape of the object and to handle collisions.
 */
public interface Collidable {
    /**
     * Returns the collision rectangle shape of the object.
     *
     * @return the collision rectangle shape of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred at the specified collision point
     * with a given velocity. Returns the new velocity expected after the collision,
     * based on the force the object inflicted on the colliding object.
     *
     * @param hitter the ball that hit the collidable object
     * @param collisionPoint  the point where the collision occurred
     * @param currentVelocity the current velocity of the colliding object
     * @return the new velocity expected after the collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}


