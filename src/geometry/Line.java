package geometry;
// 322613720 Stav Reuven

/**
 * The Line class represents a line segment defined by two points.
 * It provides methods to calculate the length, middle point, and intersections with other lines.
 */
public class Line {
    private Point start;
    private Point end;
    private final double epsilon = 0.00001;

    /**
     * Constructs a line segment with the specified start and end points.
     *
     * @param start the start point of the line segment
     * @param end   the end point of the line segment
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a line segment with the specified coordinates.
     *
     * @param x1 the x-coordinate of the start point
     * @param y1 the y-coordinate of the start point
     * @param x2 the x-coordinate of the end point
     * @param y2 the y-coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the length of the line segment.
     *
     * @return the length of the line segment
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Checks if the given point is on the line segment.
     *
     * @param p the point to check
     * @return true if the point is on the line segment, false otherwise
     */
    public boolean contains(Point p) {
        return p.isOnLine(this);
    }

    /**
     * Checks if this line is equal to another line.
     *
     * @param other the other line to compare with
     * @return true if the lines are equal, false otherwise
     */
    public boolean equal(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start));
    }

    /**
     * Returns the middle point of the line segment.
     *
     * @return the middle point of the line segment
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * Returns the start point of the line segment.
     *
     * @return the start point of the line segment
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line segment.
     *
     * @return the end point of the line segment
     */
    public Point end() {
        return this.end;
    }

    /**
     * Checks if this line intersects with another line.
     *
     * @param other the other line to check for intersection
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        return null != this.intersectionWith(other);
    }

    /**
     * Checks if this line intersects with two other lines.
     *
     * @param other1 the first line to check for intersection
     * @param other2 the second line to check for intersection
     * @return true if all three lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return isIntersecting(other1) && isIntersecting(other2);
    }

    /**
     * Returns the intersection point with another line if they intersect.
     *
     * @param other the other line to check for intersection
     * @return the intersection point if the lines intersect, null otherwise
     */
    public Point intersectionWith(Line other) {
        // If the lines are exactly the same, return null (considered no intersection)
        if (this.equals(other)) {
            return null;
        }
        // Get the coordinates of the start and end points of both lines
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();

        // Calculate the denominator of the intersection formulas
        double denominator = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);

        // If the lines have the same slope (are parallel), handle special cases
        if (this.sameSlope(other)) {
            // If the start points of both lines are the same
            if (this.start.equals(other.start)) {
                return new Point(this.start.getX(), this.start.getY());
            }
            // If the end points of both lines are the same
            if (this.end.equals(other.end)) {
                return new Point(this.end.getX(), this.end.getY());
            }
            // If the start of this line is the same as the end of the other line
            if (this.start.equals(other.end)) {
                return new Point(this.start.getX(), this.start.getY());
            }
            // If the end of this line is the same as the start of the other line
            if (this.end.equals(other.start)) {
                return new Point(this.end.getX(), this.end.getY());
            }
        }

        // Calculate the intersection factors
        double intersection1 = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / denominator;
        double intersection2 = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / denominator;

        // Check if the intersection factors are within the bounds of the line segments
        if (intersection1 >= 0 - epsilon && intersection1 <= 1 + epsilon
                && intersection2 >= 0 - epsilon && intersection2 <= 1 + epsilon) {
            // Calculate the exact intersection point
            double x = x1 + intersection1 * (x2 - x1);
            double y = y1 + intersection1 * (y2 - y1);
            return new Point(x, y);
        }

        // If no intersection within the bounds, return null
        return null;
    }

    /**
     * Checks if this line has the same slope as another line.
     *
     * @param other the other line to compare slopes with
     * @return true if the lines have the same slope, false otherwise
     */
    public boolean sameSlope(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();
        return 0 == (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
    }

    /**
     * Returns the distance from the given point to this line segment.
     *
     * @param p the point to calculate the distance from
     * @return the distance from the point to the line segment
     */
    public double distanceFromPoint(Point p) {
        // Vector from start to end of the line segment
        double lineVectorX = end.getX() - start.getX();
        double lineVectorY = end.getY() - start.getY();

        // Vector from start to point p
        double pointVectorX = p.getX() - start.getX();
        double pointVectorY = p.getY() - start.getY();

        // Dot product of the line vector and the point vector
        double dotProduct = lineVectorX * pointVectorX + lineVectorY * pointVectorY;
        double lineLengthSquared = lineVectorX * lineVectorX + lineVectorY * lineVectorY;
        double projectionFactor = dotProduct / lineLengthSquared;

        double closestX, closestY;

        if (projectionFactor < 0 || (start.getX() == end.getX() && start.getY() == end.getY())) {
            closestX = start.getX();
            closestY = start.getY();
        } else if (projectionFactor > 1) {
            closestX = end.getX();
            closestY = end.getY();
        } else {
            closestX = start.getX() + projectionFactor * lineVectorX;
            closestY = start.getY() + projectionFactor * lineVectorY;
        }

        double deltaX = p.getX() - closestX;
        double deltaY = p.getY() - closestY;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    /**
     * Returns the closest intersection point to the start of the line with a given rectangle.
     * If there is no intersection, returns null.
     *
     * @param rect the rectangle to check for intersections
     * @return the closest intersection point to the start of the line, or null if there is no intersection
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line top = new Line(rect.getUpperLeft(), rect.getUpperRight());
        Line bottom = new Line(rect.getLowerLeft(), rect.getLowerRight());
        Line left = new Line(rect.getLowerLeft(), rect.getUpperLeft());
        Line right = new Line(rect.getLowerRight(), rect.getUpperRight());
        Point p1 = null, p2 = null, p3 = null, p4 = null, p5 = null;
        if (this.isIntersecting(top)) {
            p1 = this.intersectionWith(top);
        }
        if (this.isIntersecting(bottom)) {
            p2 = this.intersectionWith(bottom);
        }
        if (this.isIntersecting(left)) {
            p3 = this.intersectionWith(left);
        }
        if (this.isIntersecting(right)) {
            p4 = this.intersectionWith(right);
        }
        if (p1 == null && p2 == null && p3 == null && p4 == null) {
            return null;
        }
        double distance = Double.MAX_VALUE;
        if ((p1 != null) && (this.start.distance(p1) < distance)) {
            distance = this.start.distance(p1);
            p5 = p1;
        }
        if ((p2 != null) && (this.start.distance(p2) < distance)) {
            distance = this.start.distance(p2);
            p5 = p2;
        }
        if ((p3 != null) && (this.start.distance(p3) < distance)) {
            distance = this.start.distance(p3);
            p5 = p3;
        }
        if ((p4 != null) && (this.start.distance(p4) < distance)) {
            p5 = p4;
        }
        return p5;
    }
}

