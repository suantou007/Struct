import java.util.Comparator;
import java.util.PriorityQueue;
public class MinPriorityQueue<T> {
    private PriorityQueue<QueueNode<T>> queue;

    public MinPriorityQueue() {
        queue = new PriorityQueue<>(new Comparator<QueueNode<T>>() {
            @Override
            public int compare(QueueNode<T> node1, QueueNode<T> node2) {
                int priorityComparison = Integer.compare(node1.getPriority(), node2.getPriority());
                if (priorityComparison == 0) {//���ȼ���ͬ��ѡ��ֵ�Ƚ�
                    return compareValues(node1.getItem(), node2.getItem());
                }
                return priorityComparison;
            }
        });
    }
    private int compareValues(T value1, T value2) {
        // �Զ����ֵ�Ƚ��߼������� T ���͵�ʵ���������ʵ��
        // ������� T ʵ���� Comparable �ӿ�
        if (value1 instanceof Comparable && value2 instanceof Comparable) {
            return ((Comparable<T>) value1).compareTo(value2);
        }
        // ��� T ���Ͳ�֧�ֱȽϣ������ʵ��������д���
        // �����׳��쳣���߲�����������
        throw new UnsupportedOperationException("Cannot compare values of type T");
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void insert(T item, int priority) {
        queue.offer(new QueueNode<>(item, priority));
    }

    public T extractMin() {
        return queue.poll().getItem();
    }

    public T peekMin() {
        return queue.peek().getItem();
    }


    private static class QueueNode<T> {
        private T item;
        private int priority;
        private static int count = 0;
        private int index;

        public QueueNode(T item, int priority) {
            this.item = item;
            this.priority = priority;
            this.index = count++;
        }

        public T getItem() {
            return item;
        }

        public int getPriority() {
            return priority;
        }

        public int getIndex() {
            return index;
        }
    }

    // ʾ���÷�
    public static void main(String[] args) {
        MinPriorityQueue<Integer> queue = new MinPriorityQueue<>();
        queue.insert(1, 3);
        queue.insert(2, 1);
        queue.insert(2, 2);
        queue.insert(3, 2);
        queue.insert(1, 2);

        while (!queue.isEmpty()) {
            System.out.println(queue.extractMin());
        }
    }
}