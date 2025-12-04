package listener;

// 322613720 Stav Reuven
/**
 * The HitNotifier interface represents an object that can notify listeners about hit events.
 * It provides methods to add and remove hit listeners.
 */
public interface HitNotifier {
    /**
     * Adds the specified HitListener to the list of listeners to hit events.
     *
     * @param hl the HitListener to add
     */
    void addHitListener(HitListener hl);

    /**
     * Removes the specified HitListener from the list of listeners to hit events.
     *
     * @param hl the HitListener to remove
     */
    void removeHitListener(HitListener hl);
}

