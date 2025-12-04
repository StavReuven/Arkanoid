package geometry;
// 322613720 Stav Reuven
import java.util.List;
import java.util.ArrayList;

/**
 * The Rectangle class represents a rectangle defined by an upper-left point, width, and height.
 * It provides methods to calculate intersection points with a line and retrieve dimensions of the rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructs a rectangle with the specified upper-left point, width, and height.
     *
     * @param upperLeft the upper-left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns a list of intersection points between this rectangle and a specified line.
     * The list may be empty if there are no intersections.
     *
     * @param line the line to check for intersection with the rectangle
     * @return a list of intersection points with the rectangle
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();

        Line top = new Line(getUpperLeft(), getUpperRight());
        if (top.isIntersecting(line)) {
            intersectionPoints.add(top.intersectionWith(line));
        }

        Line left = new Line(getUpperLeft(), getLowerLeft());
        if (left.isIntersecting(line)) {
            intersectionPoints.add(left.intersectionWith(line));
        }

        Line right = new Line(getUpperRight(), getLowerRight());
        if (right.isIntersecting(line)) {
            intersectionPoints.add(right.intersectionWith(line));
        }

        Line bottom = new Line(getLowerRight(), getLowerLeft());
        if (bottom.isIntersecting(line)) {
            intersectionPoints.add(bottom.intersectionWith(line));
        }

        return intersectionPoints;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY());
    }

    /**
     * Returns the lower-left point of the rectangle.
     *
     * @return the lower-left point of the rectangle
     */
    public Point getLowerLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY() + height);
    }

    /**
     * Returns the upper-right point of the rectangle.
     *
     * @return the upper-right point of the rectangle
     */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + width, upperLeft.getY());
    }

    /**
     * Returns the lower-right point of the rectangle.
     *
     * @return the lower-right point of the rectangle
     */
    public Point getLowerRight() {
        return new Point(this.upperLeft.getX() + width, upperLeft.getY() + height);
    }
}

