import java.util.Arrays;
import java.util.Comparator;

public class QuickSort {
    private static final int INSERTION_SORT_THRESHOLD = 10;

    public static <T extends Comparable<T>> void sort(T[] array) {

        sort(array, Comparator.naturalOrder());
    }

    public static <T> void sort(T[] array, Comparator<T> comparator) {

        sort(array, 0, array.length - 1, comparator);
    }

    private static <T> void sort(T[] array, int left, int right, Comparator<T> comparator) {
        if (left >= right) {
            return;
        }

        if (right - left + 1 <= INSERTION_SORT_THRESHOLD) {
            insertionSort(array, left, right, comparator);
            return;
        }

        int pivotIndex = choosePivotIndex(array, left, right, comparator);
        int partitionIndex = partition(array, left, right, pivotIndex, comparator);

        sort(array, left, partitionIndex - 1, comparator);
        sort(array, partitionIndex + 1, right, comparator);
    }

    private static <T> int choosePivotIndex(T[] array, int left, int right, Comparator<T> comparator) {
        int mid = left + (right - left) / 2;
        T median;
        int pivotIndex;

        if (((comparator.compare(array[mid], array[left]) > 0) && (comparator.compare(array[right], array[mid]) > 0)) ||
            ((comparator.compare(array[mid], array[right]) > 0) && (comparator.compare(array[left], array[mid]) > 0))){
            median = array[mid];
            pivotIndex = mid;
        } else if (((comparator.compare(array[left], array[mid]) > 0) && (comparator.compare(array[right], array[left]) > 0)) ||
                ((comparator.compare(array[left], array[right]) > 0) && (comparator.compare(array[mid], array[left]) > 0))){
            median = array[left];
            pivotIndex = left;
        } else {
            median = array[right];
            pivotIndex = right;
        }
        return pivotIndex;
    }

    private static <T> int partition(T[] array, int left, int right, int pivotIndex, Comparator<T> comparator) {
        T pivotValue = array[pivotIndex];
        swap(array, pivotIndex, right);

        int partitionIndex = left;

        for (int i = left; i < right; i++) {
            if (comparator.compare(array[i], pivotValue) <= 0) {
                swap(array, i, partitionIndex);
                partitionIndex++;
            }
        }

        swap(array, partitionIndex, right);

        return partitionIndex;
    }

    private static <T> void insertionSort(T[] array, int left, int right, Comparator<T> comparator) {
        for (int i = left + 1; i <= right; i++) {
            T tmp = array[i];
            int j = i - 1;

            for (; j >= 0; j--) {
                if (comparator.compare(array[j], tmp) > 0) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = tmp;
        }
    }

    private static <T> void swap(T[] array, int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        Integer[] numbers = { 5, 2, 9, 1, 3, 8, 4, 7, 6, 8, 1, 2, 9, 7, 5, 3, 7, 3, -7 };
        QuickSort.sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }
}