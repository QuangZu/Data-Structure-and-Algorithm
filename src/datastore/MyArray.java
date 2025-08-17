package datastore;

public class MyArray<T> {
    private Object[] data;
    private int size;

    public MyArray() {
        this.data = new Object[10];
        this.size = 0;
    }

    // Add element (Create)
    public void add(T element) {
        ensureCapacity();
        data[size++] = element;
    }

    // Get element (Read)
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return (T) data[index];
    }

    // Update element
    public void set(int index, T element) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        data[index] = element;
    }

    // Delete element
    public void remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[--size] = null;
    }

    // Reverse array
    public void reverse() {
        for (int i = 0; i < size / 2; i++) {
            Object temp = data[i];
            data[i] = data[size - 1 - i];
            data[size - 1 - i] = temp;
        }
    }

    // Partition: move negative numbers to left
    public void partitionNumbers() {
        int left = 0;
        int right = size - 1;
        while (left < right) {
            while (left < size && ((Number) data[left]).doubleValue() < 0) left++;
            while (right >= 0 && ((Number) data[right]).doubleValue() >= 0) right--;
            if (left < right) {
                Object temp = data[left];
                data[left] = data[right];
                data[right] = temp;
            }
        }
    }

    public int size() { return size; }

    private void ensureCapacity() {
        if (size == data.length) {
            Object[] newData = new Object[data.length * 2];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
    }

    public void printAll() {
        for (int i = 0; i < size; i++) {
            System.out.println(data[i]);
        }
    }

    public static <T> String toString(T[] array) {
        if (array == null) {
            return "null";
        }
        if (array.length == 0) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(array[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}
