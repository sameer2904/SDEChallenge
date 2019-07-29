/**
 * This class stores the stream of data and returns floating average of the elements in the window.
 * Window store N last added number in the queue.
 */
public interface MovingAverage {
    /**
     * This function add one element to the Queue of the input data stream.
     * @param data
     */
    void addElement(float data);

    /**
     * This function returns the floating average of the current elements in the window.
     * @return
     */
    float getAverage();
}
