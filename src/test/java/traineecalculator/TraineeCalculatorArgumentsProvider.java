package traineecalculator;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class TraineeCalculatorArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(Arguments.of("42", "42"),
                Arguments.of("a", "error"),
                Arguments.of("2 + 3", "5"),
                Arguments.of("2+3", "5"),
                Arguments.of("1+2+3", "6"),
                Arguments.of("3-2", "1"),
                Arguments.of("2-3", "-1"),
                Arguments.of("3++2", "error"),
                Arguments.of("3+-2", "error"),
                Arguments.of("2+3-2", "3"),
                Arguments.of("3-2+1", "2"),
                Arguments.of("5-3-2", "0"),
                Arguments.of("3+2-2+3", "6"),
                Arguments.of("0.5+0.75", "1.25"),
                Arguments.of("1 2", "error"),
                Arguments.of("3*2", "6"),
                Arguments.of("3*2*2", "12"),
                Arguments.of("4/2", "2"),
                Arguments.of("3/2", "1.5"),
                Arguments.of("3 + 2 * 5","25"),
                Arguments.of("2+a","error")
        );
    }
}
