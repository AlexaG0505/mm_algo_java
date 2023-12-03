class TokenBinaryMult extends TokenBinaryOperation {
    public int getPriority() {
        return 2;
    }
    public String getCh() {
        return "*";
    }
    public boolean isLeftAssociative() {
        return true;
    }
    public double operation(double a, double b) {
        return a * b;
    }
    public boolean isTokenBracket() {
        return false;
    }
}
