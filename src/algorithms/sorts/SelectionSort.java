package algorithms.sorts;

public class SelectionSort {
    public static <T extends Comparable<T>> void sort(T[] array, boolean ascending) {
        for (int i = 0; i < array.length - 1; i++) {
            int targetIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (ascending ? array[j].compareTo(array[targetIndex]) < 0
                              : array[j].compareTo(array[targetIndex]) > 0) {
                    targetIndex = j;
                }
            }
            T temp = array[targetIndex];
            array[targetIndex] = array[i];
            array[i] = temp;
        }
    }
}
