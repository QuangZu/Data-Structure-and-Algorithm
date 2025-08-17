package algorithms.sorts;

public class MergeSort {
    public static <T extends Comparable<T>> void sort(T[] array, boolean ascending) {
        if (array.length <= 1) return;
        mergeSort(array, 0, array.length - 1, ascending);
    }

    private static <T extends Comparable<T>> void mergeSort(T[] array, int left, int right, boolean ascending) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid, ascending);
            mergeSort(array, mid + 1, right, ascending);
            merge(array, left, mid, right, ascending);
        }
    }

    private static <T extends Comparable<T>> void merge(T[] array, int left, int mid, int right, boolean ascending) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        @SuppressWarnings("unchecked")
        T[] leftArr = (T[]) new Comparable[n1];
        @SuppressWarnings("unchecked")
        T[] rightArr = (T[]) new Comparable[n2];

        System.arraycopy(array, left, leftArr, 0, n1);
        System.arraycopy(array, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (ascending ? leftArr[i].compareTo(rightArr[j]) <= 0
                          : leftArr[i].compareTo(rightArr[j]) >= 0) {
                array[k++] = leftArr[i++];
            } else {
                array[k++] = rightArr[j++];
            }
        }
        while (i < n1) array[k++] = leftArr[i++];
        while (j < n2) array[k++] = rightArr[j++];
    }
}
