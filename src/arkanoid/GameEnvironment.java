package arkanoid;

// 322613720 Stav Reuven

import geometry.Collidable;
import geometry.CollisionInfo;
import geometry.Line;
import geometry.Point;

import java.util.List;
import java.util.ArrayList;

/**
 * The GameEnvironment class represents the collection of collidable objects
 * within the game. It manages adding collidables and detecting collisions
 * between them and a trajectory line.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Constructs a new GameEnvironment with an empty collection of collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Finds the closest collision between a trajectory line and any collidable object
     * in the game environment.
     *
     * @param trajectory the trajectory line along which an object is moving
     * @return CollisionInfo object describing the closest collision point and collidable,
     *         or null if no collision will occur
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double minDistance = Double.MAX_VALUE;
        Point closestPoint = null;
        Collidable closestCollidable = null;
        List<Collidable> copy = new ArrayList<>(this.collidables);

        // Iterate through all collidables to find the closest collision
        for (Collidable collidable : copy) {
            List<Point> intersectionPoints = collidable.getCollisionRectangle().intersectionPoints(trajectory);
            for (Point point : intersectionPoints) {
                double distance = trajectory.start().distance(point);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestCollidable = collidable;
                    closestPoint = point;
                }
            }
        }

        // Return CollisionInfo with closest collision point and collidable
        if (closestCollidable == null) {
            return null;
        }
        return new CollisionInfo(closestPoint, closestCollidable);
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c the collidable object to remove
     */
    public void remove(Collidable c) {
        this.collidables.remove(c);
    }
}
