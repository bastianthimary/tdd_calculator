package traineecalculator;

import java.util.*;

public class TDDKataCalculator {
    static List<String> SEPERATORS = new ArrayList<>(Arrays.asList(",", "\n"));

    public static int addTwoNumber(int firstNumber, int secondNumber) {
        return firstNumber + secondNumber;
    }

    public static int addStringExpression(String addExpression) {
        var ref = new Object() {
            int additionResult = 0;
        };
        convertStringExpressionToIntegers(addExpression).
                forEach(i -> ref.additionResult = ref.additionResult + i);
        return ref.additionResult;
    }

    private static List<Integer> convertStringExpressionToIntegers(String expression) {
        List<Integer> setOfConvertedInteger = new ArrayList<>();
        if (expression.isEmpty()) {
            setOfConvertedInteger.add(0);
            return setOfConvertedInteger;
        }
        if (containsSeperators(expression)) {
            split(expression).forEach(i -> setOfConvertedInteger.add(Integer.parseInt(i)));
        } else {
            setOfConvertedInteger.add(Integer.parseInt(expression));
        }
        return setOfConvertedInteger;
    }

    private static List<String> split(String expression) {
        List<String> splits = new ArrayList<>();
        splitForSeparator(expression,",").
                forEach(split->splits.addAll(splitForSeparator(split,"\n")));
        return splits;
    }
    private static List<String> splitForSeparator(String expression,String seperator){
        return Arrays.asList(expression.split(seperator));
    }

    private static boolean containsSeperators(String expression) {
        return SEPERATORS.stream().anyMatch(expression::contains);
    }

}
