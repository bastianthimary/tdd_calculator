package traineecalculator.term.termpoint;

import traineecalculator.term.Operator;

public class TermPoint {

    private final char token;

    public TermPoint(char token) {
        this.token = token;
    }

    public char getToken() {
        return token;
    }
    public boolean isAWhitespace() {
        return Character.isWhitespace(token);
    }
    public boolean isANumber() {
        return Character.isDigit(token);
    }
    public boolean isAOperator() {
        return Operator.isAOperator(String.valueOf(token));
    }
    public boolean isADecimalSeparator() {
        return String.valueOf(token).contains(TermPointSeperator.SEPERATOR);
    }
}
