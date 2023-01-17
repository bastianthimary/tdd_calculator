package traineecalculator.term.termelement;

import traineecalculator.CalculatorException;
import traineecalculator.term.PreviousPointState;
import traineecalculator.term.termpoint.TermPoint;
import traineecalculator.term.termpoint.TermPointSeperator;

import java.math.BigDecimal;

public class TermElementNumber extends TermElement {
    private BigDecimal numbervalue;
    private PreviousPointState previousPointState;

    public TermElementNumber(TermPoint point) throws CalculatorException {
        super();
        if (point.isANumber()) {
            stringValue = String.valueOf(point.token());
            numbervalue = new BigDecimal(stringValue);
            previousPointState = PreviousPointState.NUMBER;
        } else {
            errorReport();
        }
    }

    private void errorReport() throws CalculatorException {
        throw new CalculatorException();
    }

    public BigDecimal getNumbervalue() {
        return numbervalue;
    }

    public void appendDigitToLastPositionOfNumber(TermPoint rearPosition) throws CalculatorException {
        if (rearPosition.isANumber()) {
            if (previousPointIsASeperator()) {
                appendSeperator();
            } else if (previousPointIsAWhitespace()) {
                appendWhitespace();
            }
            appendNumber(rearPosition);
        } else {
            errorReport();
        }
    }

    private void appendNumber(TermPoint rearPosition) {
        stringValue = stringValue + rearPosition.token();
        numbervalue = new BigDecimal(stringValue);
    }

    private void appendWhitespace() throws CalculatorException {
        errorReport();
    }

    private boolean previousPointIsAWhitespace() {
        return PreviousPointState.WHITE_SPACE.equals(previousPointState);
    }

    private boolean previousPointIsASeperator() {
        return PreviousPointState.DECIMAL_SEPERATOR.equals(previousPointState);
    }

    private void appendSeperator() {
        stringValue = stringValue + TermPointSeperator.SEPERATOR;
        previousPointState = PreviousPointState.NUMBER;
    }

    public void setPreviousPointAsWhitespace() {
        previousPointState = PreviousPointState.WHITE_SPACE;
    }

    public void setPreviousPointAsSeperator() {
        previousPointState = PreviousPointState.DECIMAL_SEPERATOR;
    }

}
