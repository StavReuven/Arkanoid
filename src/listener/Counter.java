package listener;

// 322613720 Stav Reuven
/**
 * The Counter class represents a simple counter that can be increased, decreased,
 * and queried for its current value.
 */
public class Counter {
    private int count; // Current count value

    /**
     * Constructs a new Counter with an initial count of 0.
     */
    public Counter() {
        count = 0;
    }

    /**
     * Increases the current count by the given number.
     *
     * @param number the number to increase the count by
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * Decreases the current count by the given number.
     *
     * @param number the number to decrease the count by
     */
    public void decrease(int number) {
        count -= number;
    }

    /**
     * Returns the current count value.
     *
     * @return the current count value
     */
    public int getValue() {
        return count;
    }
}

