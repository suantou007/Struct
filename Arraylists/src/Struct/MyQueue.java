package Struct;

import java.util.ArrayList;
import java.util.List;

public class MyQueue<T> {
    private List<T>[] buckets;
    private int size;

    public MyQueue(int numBuckets, int initialSize) {
        buckets = new List[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            buckets[i] = new ArrayList<>();
        }
        size = initialSize;
    }

    public boolean isEmpty(int bucketIndex) {
        return buckets[bucketIndex].isEmpty();
    }

    public void enqueue(int bucketIndex, T item) {
        buckets[bucketIndex].add(item);
    }

    public T dequeue(int bucketIndex) {
        if (isEmpty(bucketIndex)) {
            throw new IllegalStateException("Queue is empty");
        }
        return buckets[bucketIndex].remove(0);
    }
    public int size(int n){
        return buckets[n].size();
    }
    public int size() {
        return size;
    }
}