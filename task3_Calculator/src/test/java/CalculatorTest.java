import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    @Test
    @DisplayName("Test case: addition test")
    public void testAddition() {
        Calculator calculator = new Calculator();
        double result = calculator.processCalculator("2 + 3");
        Assertions.assertEquals(5.0, result);
    }

    @Test
    @DisplayName("Test case: subtraction test")
    public void testSubtraction() {
        Calculator calculator = new Calculator();
        double result = calculator.processCalculator("5 - 2");
        Assertions.assertEquals(3.0, result);
    }

    @Test
    @DisplayName("Test case: multiplication test")
    public void testMultiplication() {
        Calculator calculator = new Calculator();
        double result = calculator.processCalculator("3 * 4");
        Assertions.assertEquals(12.0, result);
    }

    @Test
    @DisplayName("Test case: division test")
    public void testDivision() {
        Calculator calculator = new Calculator();
        double result = calculator.processCalculator("10 / 2");
        Assertions.assertEquals(5.0, result);
    }

    @Test
    @DisplayName("Test case: exponentiation test")
    public void testExponentiation() {
        Calculator calculator = new Calculator();
        double result = calculator.processCalculator("2 ^ 3");
        Assertions.assertEquals(8.0, result);
    }

    @Test
    @DisplayName("Test case: test expression with different operations")
    public void testComplexExpression() {
        Calculator calculator = new Calculator();
        double result = calculator.processCalculator("2 + 3 * 4 - 5 / 2");
        Assertions.assertEquals(11.5, result);
    }

    @Test
    @DisplayName("Test case: test expression with all operations and brackets")
    public void testComplexExpressionWithBrackets() {
        Calculator calculator = new Calculator();
        double result = calculator.processCalculator("2 + 3 * 4 - ( ( 6 / 2 + 4 ) / 7 ) ^ 2");
        Assertions.assertEquals(13, result);
    }

    @Test
    @DisplayName("Test case: test incorrect input")
    public void testIncorrectInput() {
        Calculator calculator = new Calculator();
        double result = calculator.processCalculator("2 + + 3");
        Assertions.assertTrue(calculator.errorFlag);
    }

    @Test
    @DisplayName("Test case: test infinity error")
    public void testDivisionByZero() {
        Calculator calculator = new Calculator();
        double result = calculator.processCalculator("5 / 0");
        Assertions.assertTrue(calculator.errorFlag);
    }

    @Test
    @DisplayName("Test case: test wrong brackets")
    public void testMismatchedBrackets() {
        Calculator calculator = new Calculator();
        double result = calculator.processCalculator("(2 + 3");
        Assertions.assertTrue(calculator.errorFlag);
    }
}
