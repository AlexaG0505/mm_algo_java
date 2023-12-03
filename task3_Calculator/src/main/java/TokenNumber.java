class TokenNumber extends Token {
    public double value;

    public double getValue() {
        return value;
    }
    public String getCh() {
        return String.valueOf(value);
    }
    public void setValue(double value) {
        this.value = value;
    }
    public boolean isTokenBracket() {
        return false;
    }
    public boolean isNumber() {
        return true;
    }
    public boolean isBinaryOperation() {
        return false;
    }
}
