package animations;
/** this class represent selection , every selection have key, massage, return value.
 * @param <T> is the value of the selection.
 * */
public class Selection<T> {
    private String key;
    private String message;
    private T returnVal;
    /** accept key, massage, and return value and create selection.
     * @param key the key to select this selection.
     * @param message is a message that represent this selection.
     * */
    public Selection(String key, String message, T returnVal) {
        this.key = key;
        this.message = message;
        this.returnVal = returnVal;
    }
    /** return the value of this selection.
     * @return the value of this selection.
     * */
    public T getReturnVal() {
        return returnVal;
    }
    /** return the key of this selection.
     *  @return the key of this selection.
     * */
    public String getKey() {
        return key;
    }
    /** return massage that represent this selection.
     * @return massage that represent this selection.
     */
    public String getMessage() {
        return message;
    }
}
