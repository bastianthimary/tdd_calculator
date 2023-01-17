package traineecalculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import traineecalculator.TDDKataCalculator;

import static org.assertj.core.api.Assertions.assertThat;

class TDDKataCalculatorTest {
    @Test
    void addTwoNumbers() {
        int firstNumber = 1;
        int secondNumber = 2;

        int twoNumberAddResult = TDDKataCalculator.addTwoNumber(firstNumber, secondNumber);

        assertThat(twoNumberAddResult).isEqualTo(3);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "casesForTDDKataCalculatorTest.csv",delimiter = ';')
    void addStringExpression(String input,int expected) {

        int singleNumberResult = TDDKataCalculator.addStringExpression(input);

        assertThat(singleNumberResult).isEqualTo(expected);
    }
    @Test
    void addStringNumbers() {
        String singleNumberString = "1";

        int singleNumberResult = TDDKataCalculator.addStringExpression(singleNumberString);

        assertThat(singleNumberResult).isEqualTo(1);
    }

    @Test
    void addEmptyString() {
        String emptyString = "";

        int emptyStringResult = TDDKataCalculator.addStringExpression(emptyString);

        assertThat(emptyStringResult).isEqualTo(0);
    }

    @Test
    void addTwoNumberWithComma() {
        String twoNumberSeperateByComma = "1,2";

        int resultOfTwoNumbers = TDDKataCalculator.addStringExpression(twoNumberSeperateByComma);

        assertThat(resultOfTwoNumbers).isEqualTo(3);
    }

    @Test
    void addSeveralNumberWithComma() {
        String severalNumberSepeateByComma = "1,2,3,4";

        int resultOfSeveralNumbers = TDDKataCalculator.addStringExpression(severalNumberSepeateByComma);

        assertThat(resultOfSeveralNumbers).isEqualTo(10);
    }

    @Test
    void addNumbersWithNewLine() {
        String numbersWithNewLine = "1\n2,3";

        int resultOfNumbersWithNewLine = TDDKataCalculator.addStringExpression(numbersWithNewLine);

        assertThat(resultOfNumbersWithNewLine).isEqualTo(6);
    }
}