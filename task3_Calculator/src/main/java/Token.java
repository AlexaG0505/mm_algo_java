abstract class Token {
    public abstract boolean isTokenBracket();
    public abstract boolean isNumber();
    public abstract boolean isBinaryOperation();
    public abstract String getCh();
}