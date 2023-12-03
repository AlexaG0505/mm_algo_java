import java.io.IOException;
import java.util.*;

public class Calculator {
    public boolean errorFlag;
    Double positiveInfinity = Double.POSITIVE_INFINITY;
    Double negativeInfinity = Double.NEGATIVE_INFINITY;

    /**
     * iterates over tokens and, depending on the token type, calls appropriate methods to handle operations, brackets and numbers
     * @param tokens - tokens array for sorting station
     */
    private Deque<Token> sortingStation(String[] tokens) {
        Deque<Token> operatorStack = new ArrayDeque<Token>();
        Deque<Token> outputQueue = new ArrayDeque<Token>();

        for (int n = 0; n < tokens.length; n++) {
            String str = tokens[n];
            char symbol = str.charAt(0);
            if (symbol >= '0' && symbol <= '9') {
                TokenNumber tokenNumber = new TokenNumber();
                tokenNumber.setValue(Double.parseDouble(str));
                outputQueue.add(tokenNumber);
            } else if (symbol == '*') {
                Token tokenOperation = new TokenBinaryMult();
                moveTokenBinaryOperationInQueue(outputQueue, operatorStack, (TokenBinaryOperation) tokenOperation);
            } else if (symbol == '/') {
                Token tokenOperation = new TokenBinaryDiv();
                moveTokenBinaryOperationInQueue(outputQueue, operatorStack, (TokenBinaryOperation) tokenOperation);
            } else if (symbol == '+') {
                Token tokenOperation = new TokenBinaryPlus();
                moveTokenBinaryOperationInQueue(outputQueue, operatorStack, (TokenBinaryOperation) tokenOperation);
            } else if (symbol == '-') {
                Token tokenOperation = new TokenBinaryMinus();
                moveTokenBinaryOperationInQueue(outputQueue, operatorStack, (TokenBinaryOperation) tokenOperation);
            } else if (symbol == '^') {
                Token tokenOperation = new TokenBinaryPow();
                moveTokenBinaryOperationInQueue(outputQueue, operatorStack, (TokenBinaryOperation) tokenOperation);
            } else if (symbol == '(') {
                Token tokenBracket = new TokenBracketOpen();
                operatorStack.push(tokenBracket);
            } else if (symbol == ')') {
                fillQueueUntilMatchingBracket(outputQueue, operatorStack);
            }
        }

        while (!operatorStack.isEmpty()) {
            if (operatorStack.peekFirst().isTokenBracket()) {
                System.out.println("Incorrect input, wrong brackets");
                errorFlag = true;
                break;
            }
            Token processOper = operatorStack.peekFirst();
            outputQueue.add(processOper);
            operatorStack.pop();
        }
        return outputQueue;
    }

    /**
     * accepts an output queue and performs calculations using a stack of numbers
     *   retrieves tokens from sorting station and, if the token is a number, pushes it onto the number stack,
     *   if the token is a binary operation, pops two numbers from the stack and pushes the result back onto the number stack
     * @param outputQueue - result of sorting station
     * @return - last (unique) number stack value
     */
    private double stackCalculator(Deque<Token> outputQueue) {
        Deque<Token> numberStack = new ArrayDeque<Token>();

        try {
        while (!outputQueue.isEmpty()) {
                Token processToken = outputQueue.peek();
                if (processToken.isNumber())
                    numberStack.push(processToken);

                else if (processToken instanceof TokenBinaryOperation binaryOper) {
                    Token numToken = numberStack.pop();
                    double num2 = ((TokenNumber) numToken).getValue();
                    numToken = numberStack.pop();
                    double num1 = ((TokenNumber) numToken).getValue();
                    double operResult = binaryOper.operation(num1, num2);

                    if ((operResult == positiveInfinity) || (operResult == negativeInfinity)) {
                        errorFlag = true;
                        System.out.print("Infinity error");
                    }

                    Token newTokenNum = new TokenNumber();
                    ((TokenNumber) newTokenNum).setValue(operResult);
                    numberStack.push(newTokenNum);
                }
                outputQueue.pop();
            }
        }
        catch (Exception exc) {
            errorFlag = true;
            System.out.println("Input error");
        }

        if (numberStack.size() == 1) {
            return ((TokenNumber) numberStack.pop()).getValue();
        } else {
            System.out.println("Calculation error");
            errorFlag = true;
        }
        return 0;
    }

    /**
     * processes binary operations, compares the priority of the current operation with the operations in stack, and, if necessary, moves operations from the stack to the output queue
     * @param outputQueue
     * @param operatorStack
     * @param tokenOperation - processing binary operation
     */
    private void moveTokenBinaryOperationInQueue(Deque<Token> outputQueue, Deque<Token> operatorStack, TokenBinaryOperation tokenOperation) {
        while (!operatorStack.isEmpty() && !operatorStack.peekFirst().isTokenBracket()) {
            int priorityOper = tokenOperation.getPriority();
            Token stackOperation = operatorStack.peekFirst();
            int priorityStackOper = ((TokenBinaryOperation) stackOperation).getPriority();
            if (priorityOper < priorityStackOper || ((priorityOper == priorityStackOper) && tokenOperation.isLeftAssociative())) {
                outputQueue.add(stackOperation);
                operatorStack.pop();
            } else {
                break;
            }
        }
        operatorStack.push(tokenOperation);
    }

    /**
     * processes a closing bracket, moves operations from the stack to the output queue until open bracket
     * @param outputQueue
     * @param operatorStack
     */
    private void fillQueueUntilMatchingBracket(Deque<Token> outputQueue, Deque<Token> operatorStack) {
        while (!operatorStack.isEmpty() && !operatorStack.peekFirst().isTokenBracket()) {
            Token processOper = operatorStack.peekFirst();
            outputQueue.add(processOper);
            operatorStack.pop();
        }

        if (!operatorStack.isEmpty() && operatorStack.peekFirst().isTokenBracket()) {
            operatorStack.pop();
        } else {
            System.out.println("Incorrect input, wrong brackets");
            errorFlag = true;
        }
    }

    public double processCalculator(String input) {
        String[] tokens = input.split(" ");
        Deque<Token> outputQueue = sortingStation(tokens);
        return stackCalculator(outputQueue);
    }

    public static void main(String[] args) {
        System.out.print("The expression must have space delimiters.\n");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter expression: ");
        String expression = input.nextLine();
        Calculator process = new Calculator();
        double result = process.processCalculator(expression);

        if (!process.errorFlag)
            System.out.println("Result: " + result);
    }
}