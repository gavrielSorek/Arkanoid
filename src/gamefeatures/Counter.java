//ID 318525185.
package gamefeatures;
/**this class is a simple counter.
 * */
public class Counter {
    private int value = 0;
    /**add number to current count.
     * @param number the number to add to current count.
     * */
    public void increase(int number) {
        this.value = this.value + number;
    }
    /**subtract number from current count.
     * @param number the number to subtract from the current count.
     * */
    public void decrease(int number) {
        this.value = getValue() - number;
    }
    /**get current count.
     * @return the current count.
     * */
    public int getValue() {
        return this.value;
    }
    /** set value with the gaven number.
     * @param num the gaven number.
     * */
    public void setValue(int num) {
        this.value = num;
    }
}