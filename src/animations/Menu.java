//ID 318525185
package animations;
/** this interface represent a menu animation, and can add selections to the menu, and return selection.
 * @param <T> is the return value of the menu.
 * */
public interface Menu<T> extends Animation {
    /** add a selection to the menu.
     * @param key to wait for.
     * @param message line to print.
     * @param returnVal what task to return.
     * */
    void addSelection(String key, String message, T returnVal);
    /** return the task that chosen.
     * @return the task that chosen.
     * */
    T getStatus();
}