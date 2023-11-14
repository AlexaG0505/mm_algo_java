import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Comparator;
import org.junit.jupiter.api.DisplayName;

public class BinarySearchTest {
    @Test
    @DisplayName("Test case: the element is in an array")
    public void ElementInArray() {
        Integer[] array = {1, 2, 3, 4, 5};
        int element1 = 3;
        Comparator<Integer> comparator1 = Integer::compareTo;
        Integer result1 = BinarySearch.find(array, comparator1, element1);
        assertEquals(Integer.valueOf(3), result1);
    }

    @Test
    @DisplayName("Test case: the element is not in an array")
    public void ElementNotInArray() {
        Integer[] array = {1, 2, 3, 4, 5};
        int element2 = 6;
        Comparator<Integer> comparator2 = Integer::compareTo;
        Integer result2 = BinarySearch.find(array, comparator2, element2);
        assertEquals(null, result2);
    }

    @Test
    @DisplayName("Test case: the element is not in an array")
    public void SearchingElementOnlyInArray() {
        Integer[] array3 = {4};
        int element3 = 4;
        Comparator<Integer> comparator3 = Integer::compareTo;
        Integer result3 = BinarySearch.find(array3, comparator3, element3);
        assertEquals(Integer.valueOf(4), result3);
    }

    @Test
    @DisplayName("Test case: empty array")
    public void EmptyArray() {
        Integer[] array4 = {};
        int element4 = 2;
        Comparator<Integer> comparator4 = Integer::compareTo;
        Integer result4 = BinarySearch.find(array4, comparator4, element4);
        assertEquals(null, result4);
    }
}