//ID 318525185.
package geometry;
import listeners.HitListener;
/** this interface represent hit notifier object.
 * */
public interface HitNotifier {
    /**Add hl as a listener to hit events.
     * @param hl the listener to add.
     * */
    void addHitListener(HitListener hl);
    /**Remove hl from the list of listeners to hit events.
     * @param hl the listener to remove.
     * */
    void removeHitListener(HitListener hl);
}