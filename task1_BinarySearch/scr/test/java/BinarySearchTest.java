import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Comparator;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BinarySearchTest {
    @Test
    @DisplayName("Test case: the element is in an array")
    public void elementInArray() {
        Integer[] array = {1, 2, 3, 4, 5};
        int element = 3;
        Comparator<Integer> comparator = Integer::compareTo;
        Integer result = BinarySearch.find(array, comparator, element);
        assertEquals(Integer.valueOf(3), result);
    }

    @Test
    @DisplayName("Test case: the element is not in an array")
    public void elementNotInArray() {
        Integer[] array = {1, 2, 3, 4, 5};
        int element = 6;

        Comparator<Integer> comparator = Integer::compareTo;
        Integer result = BinarySearch.find(array, comparator, element);
        assertEquals(null, result);
    }

    @Test
    @DisplayName("Test case: the unique element is in an array")
    public void searchingElementUniqueInArray() {
        Integer[] array = {4};
        int element = 4;

        Comparator<Integer> comparator = Integer::compareTo;
        Integer result = BinarySearch.find(array, comparator, element);
        assertEquals(Integer.valueOf(4), result);
    }

    @Test
    @DisplayName("Test case: the element is not in an array")
    public void severalEqualSearchingElementsInArray() {
        Integer[] array = {1, 4, 2, 3, 4, 5};
        int element = 4;

        Comparator<Integer> comparator = Integer::compareTo;
        Integer result = BinarySearch.find(array, comparator, element);
        assertEquals(Integer.valueOf(4), result);
    }

    @Test
    @DisplayName("Test case: empty array")
    public void emptyArray() {
        Integer[] array = {};
        int element = 2;

        Comparator<Integer> comparator = Integer::compareTo;
        Integer result = BinarySearch.find(array, comparator, element);
        assertEquals(null, result);
    }

    @Test
    @DisplayName("Test case: the string is in an array of strings")
    public void stringElementInArray() {
        String[] array = {"macaque", "chimpanzee", "monkey", "gorilla"};
        String element = "monkey";

        Comparator<String> comparator = Comparator.naturalOrder();
        String result = BinarySearch.<String>find(array, comparator, element);
        assertEquals(element, result);
    }

    @Test
    @DisplayName("Test case: the string is not in an array of strings")
    public void stringElementNotInArray() {
        String[] array = {"macaque", "chimpanzee", "monkey", "gorilla"};
        String element = "primate";

        Comparator<String> comparator = Comparator.naturalOrder();
        String result = BinarySearch.<String>find(array, comparator, element);
        assertEquals(null, result);
    }
}