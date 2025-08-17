package algorithms.searches;

public class BinarySearch {
    public static <T extends Comparable<T>> int search(T[] array, T target) {
        if (array == null || target == null) return -1;
        
        int left = 0;
        int right = array.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = array[mid].compareTo(target);
            
            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
