package algorithms.sorts;

public class QuickSort {
    public static <T extends Comparable<T>> void sort(T[] array, boolean ascending) {
        quickSort(array, 0, array.length - 1, ascending);
    }

    private static <T extends Comparable<T>> void quickSort(T[] array, int low, int high, boolean ascending) {
        if (low < high) {
            int pi = partition(array, low, high, ascending);
            quickSort(array, low, pi - 1, ascending);
            quickSort(array, pi + 1, high, ascending);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] array, int low, int high, boolean ascending) {
        T pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (ascending ? array[j].compareTo(pivot) <= 0 : array[j].compareTo(pivot) >= 0) {
                i++;
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        T temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }
}
