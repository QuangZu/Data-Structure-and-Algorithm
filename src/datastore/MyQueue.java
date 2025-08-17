package datastore;

public class MyQueue<T> {
    private Object[] data;
    private int head, tail, size;

    public MyQueue(int capacity) {
        data = new Object[capacity];
        head = tail = size = 0;
    }

    public boolean enqueue(T item) {
        if (size == data.length) return false;
        data[tail] = item;
        tail = (tail + 1) % data.length;
        size++;
        return true;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (size == 0) return null;
        T item = (T) data[head];
        head = (head + 1) % data.length;
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printAll() {
        if (size == 0) {
            System.out.println("Queue is empty.");
            return;
        }
        int index = head;
        for (int i = 0; i < size; i++) {
            System.out.println(data[index]);
            index = (index + 1) % data.length;
        }
    }
}
