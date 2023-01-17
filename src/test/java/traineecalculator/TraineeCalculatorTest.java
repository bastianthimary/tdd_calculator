package traineecalculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.Assertions.assertThat;

class TraineeCalculatorTest {
    TraineeCalculator calculator= new TraineeCalculator();

    @ParameterizedTest
    @ArgumentsSource(TraineeCalculatorArgumentsProvider.class)
    public void testTypingNewTerm(String term, String expected){

        calculator.typingNewTerm(term);

        assertThat(calculator.validateTermCalculateAndReturnResult()).isEqualTo(expected);

    }

}