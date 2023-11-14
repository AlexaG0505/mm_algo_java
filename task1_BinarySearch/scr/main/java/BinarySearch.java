import java.util.Comparator;

public class BinarySearch {
    public static <T> T find(T[] array, Comparator<T> comparator, T element) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparisonResult = comparator.compare(array[mid], element);

            if (comparisonResult == 0) {
                return array[mid];
            } else if (comparisonResult < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5};
        int element = 3;

        Comparator<Integer> comparator = Integer::compareTo;
        Integer result = find(array, comparator, element);

        if (result != null) {
            System.out.println("Элемент найден: " + result);
        } else {
            System.out.println("Элемент не найден.");
        }
    }
}
