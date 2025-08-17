package algorithms.searches;

public class LinearSearch {
    public static <T> int search(T[] array, T target) {
        if (array == null) return -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
}
