package geometry;
// 322613720 Stav Reuven

/**
 * The Velocity class specifies the change in position on the `x` and `y` axes.
 * It encapsulates the velocity components `dx` (change in x) and `dy` (change in y).
 */
public class Velocity {
    private double dx; // the change in position on the x axis
    private double dy; // the change in position on the y axis

    /**
     * Constructs a velocity with the specified dx and dy values.
     *
     * @param dx the change in position on the x axis
     * @param dy the change in position on the y axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Returns a new point with position (x+dx, y+dy).
     * Applies this velocity to a given point.
     *
     * @param p the point to apply the velocity to
     * @return a new point with the updated position
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Sets the change in position on the x axis.
     *
     * @param dx the new value for dx
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Sets the change in position on the y axis.
     *
     * @param dy the new value for dy
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Returns the change in position on the x axis.
     *
     * @return the value of dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * Returns the change in position on the y axis.
     *
     * @return the value of dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * Constructs a velocity from an angle and speed.
     * Calculates dx and dy based on the angle (in degrees) and speed.
     *
     * @param angle the angle of movement in degrees
     * @param speed the speed of movement
     * @return a new Velocity object with the calculated dx and dy
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle));
        double dy = speed * Math.sin(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * Calculates and returns the magnitude (speed) of this velocity.
     *
     * @return the magnitude of the velocity
     */
    public double getVelocity() {
        return Math.sqrt(Math.pow(this.getDx(), 2) + Math.pow(this.getDy(), 2));
    }
}
