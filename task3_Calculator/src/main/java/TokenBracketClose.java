class TokenBracketClose extends Token {
    public String getCh() {
        return ")";
    }
    public boolean isTokenBracket() {
        return true;
    }
    public boolean isNumber() {
        return false;
    }
    public boolean isBinaryOperation() {
        return false;
    }
}
