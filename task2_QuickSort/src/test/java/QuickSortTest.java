import java.util.Arrays;
import java.util.Comparator;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;

public class QuickSortTest {
    @Test
    @DisplayName("Test case: integers quicksort")
    public void testIntegerSort() {
        Integer[] numbers = {5, 2, 9, 1, 3, 8, 4, 7, 6, 8, 1, 2, 9, 7, 5, 3, 7, 3, -7};
        Integer[] expected = {-7, 1, 1, 2, 2, 3, 3, 3, 4, 5, 5, 6, 7, 7, 7, 8, 8, 9, 9};

        QuickSort.sort(numbers);
        Assert.assertArrayEquals(expected, numbers);
    }

    @Test
    @DisplayName("Test case: integers insertion sort")
    public void testIntegerInsertionSort() {
        Integer[] numbers = {5, 2, 9, 8, 1, 2, 7, 3, -7};
        Integer[] expected = {-7, 1, 2, 2, 3, 5, 7, 8, 9};

        QuickSort.sort(numbers);
        Assert.assertArrayEquals(expected, numbers);
    }

    @Test
    @DisplayName("Test case: strings quicksort")
    public void testStringSort() {
        String[] words = {"grapes", "raspberry", "blackberry", "watermelon", "cranberry", "blueberry", "cherry", "gooseberry", "pear", "plum", "elderberry"};
        String[] expected = {"blackberry", "blueberry", "cherry", "cranberry", "elderberry", "gooseberry", "grapes", "pear", "plum", "raspberry", "watermelon"};

        QuickSort.sort(words);
        Assert.assertArrayEquals(expected, words);
    }

    @Test
    @DisplayName("Test case: strings insertion sort")
    public void testStringInsertionSort() {
        String[] words = {"macaque", "chimpanzee", "monkey", "gorilla"};
        String[] expected = {"chimpanzee", "gorilla", "macaque", "monkey"};

        QuickSort.sort(words);
        Assert.assertArrayEquals(expected, words);
    }
}