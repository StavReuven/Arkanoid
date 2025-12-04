package geometry;
// 322613720 Stav Reuven

/**
 * Represents a point in a 2D space.
 * Provides methods to calculate the distance to another point, check equality with another point,
 * and determine if the point lies on a given line segment.
 */
public class Point {
    private double x;
    private double y;
    private double epsilon = 0.00001;

    /**
     * Constructs a Point with the specified x and y coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance from this point to another point.
     *
     * @param other the other point
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    /**
     * Checks if this point is equal to another point.
     * Points are considered equal if their x and y coordinates are within a small epsilon range.
     *
     * @param other the other point
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return (Math.abs(this.y - other.y) <= epsilon) && (Math.abs(this.x - other.x) <= epsilon);
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Checks if this point lies on a given line segment.
     * The point is considered to be on the line segment if the sum of its distances
     * to the start and end points of the line segment is approximately equal to the length of the line segment.
     *
     * @param line the line segment
     * @return true if this point lies on the line segment, false otherwise
     */
    public boolean isOnLine(Line line) {
        return Math.abs(this.distance(line.start()) + this.distance(line.end())
                - line.start().distance(line.end())) <= this.epsilon;
    }

    /**
     * Sets the x-coordinate of this point.
     *
     * @param x the new x-coordinate
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of this point.
     *
     * @param y the new y-coordinate
     */
    public void setY(double y) {
        this.y = y;
    }
}
