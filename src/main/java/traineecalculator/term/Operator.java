package traineecalculator.term;

import java.util.Arrays;

public enum Operator {

    PLUS("+", "\\+"),
    MINUS("-", "\\-"),
    MUILTIPLY("*","\\*"),
    DEVIDER("/","\\/");
    public final String sign;
    public final String regex;

    Operator(String sign, String regex) {
        this.sign = sign;
        this.regex = regex;

    }

    public static Operator valueOfSign(String sign) {
        return Arrays.stream(Operator.values()).
                filter(operator -> operator.sign.equals(sign)).
                findFirst().get();
    }

    public static boolean isAOperator(String string) {
        return Arrays.stream(Operator.values()).anyMatch(operator -> string.contains(operator.sign));
    }

}
