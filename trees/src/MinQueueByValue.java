import java.util.PriorityQueue;

public class MinQueueByValue<T extends Comparable<T>> {
    private PriorityQueue<T> queue;

    public MinQueueByValue() {
        queue = new PriorityQueue<>();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void insert(T item) {
        queue.offer(item);
    }

    public T extractMin() {
        return queue.poll();
    }

    public T peekMin() {
        return queue.peek();
    }

    // Ê¾ÀýÓÃ·¨
    public static void main(String[] args) {
        MinQueueByValue<Integer> queue = new MinQueueByValue<>();
        queue.insert(1);
        queue.insert(5);
        queue.insert(2);
        queue.insert(10);
        queue.insert(3);

        while (!queue.isEmpty()) {
            System.out.println(queue.extractMin());
        }
    }
}