abstract class TokenBinaryOperation extends Token {
    public abstract int getPriority();
    public abstract boolean isLeftAssociative();
    public abstract double operation(double a, double b);

    public boolean isNumber() {
        return false;
    }
    public boolean isBinaryOperation() {
        return true;
    }
}
