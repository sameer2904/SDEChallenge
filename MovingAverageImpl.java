import java.util.LinkedList;
import java.util.Queue;

public class MovingAverageImpl implements MovingAverage {

    /**
     * Holds the elements from the stream.
     */
    private Queue<Float> queue;
    /**
     * Window size for the queue.
     */
    private int averageSize;
    /**
     * current floating average.
     */
    private float avg;

    public MovingAverageImpl(int averageSize) {
        queue = new LinkedList<>();
        this.averageSize = averageSize;
        this.avg = 0;
    }

    @Override
    public void addElement(float data) {
        if (queue.size() == averageSize) {
            float dataToRemove = queue.poll();
            this.avg = (this.averageSize * avg - dataToRemove + data)/this.averageSize;
        } else {
            this.avg = (queue.size() * avg + data)/(queue.size()+1);
            queue.add(data);
        }
    }

    @Override
    public float getAverage() {
        return this.avg;
    }
}
