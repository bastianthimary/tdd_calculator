package traineecalculator;

import traineecalculator.term.*;
import traineecalculator.term.termelement.TermElement;
import traineecalculator.term.termelement.TermElementNumber;
import traineecalculator.term.termelement.TermElementOperator;
import traineecalculator.term.termpoint.TermPoint;

import java.math.BigDecimal;

public class TraineeCalculator {
    private Term term;
    private BigDecimal currentResultValue;
    private Operator currentOperator;
    private TermElement previousTermElement = null;
    private TermPoint currentTermPoint;
    private static final String ERROR_MESSAGE = "error";

    public void typingNewTerm(String newterm) {
        term = new Term(newterm);
    }

    public String validateTermCalculateAndReturnResult() {
        try {
            calculateTerm();
            return currentResultValue + "";
        } catch (CalculatorException e) {
            return ERROR_MESSAGE;
        }
    }

    private void calculateTerm() throws CalculatorException {
        currentResultValue = BigDecimal.ZERO;
        for (TermPoint currentPoint : term.getTermPoints()) {
            currentTermPoint =currentPoint;
            calcIfPracticable();
            parseCharAsPreviousTermElement();
        }
        calculateTillCurrentPosition();
    }

    private void calcIfPracticable() {
        if (isAbleToCalculate()) {
            calculateTillCurrentPosition();
        }
    }

    private boolean isAbleToCalculate() {
        return currentTermPoint.isAOperator();
    }

    private void calculateTillCurrentPosition() {
        if (currentOperator == null) {
            if (previousTermElement instanceof TermElementNumber) {
                currentResultValue = ((TermElementNumber) previousTermElement).getNumbervalue();
            }
        } else {
            switch (currentOperator) {
                case PLUS -> addNumbers();
                case MINUS -> subNumbers();
                case MUILTIPLY -> multiplyNumbers();
                case DEVIDER -> devideNumbers();
            }
        }
    }

    private void addNumbers() {
        if (previousTermElement instanceof TermElementNumber) {
            currentResultValue = currentResultValue.add(((TermElementNumber) previousTermElement).getNumbervalue());
        }
    }

    private void subNumbers() {
        if (previousTermElement instanceof TermElementNumber) {
            currentResultValue = currentResultValue.subtract(((TermElementNumber) previousTermElement).getNumbervalue());
        }
    }

    private void multiplyNumbers() {
        if (previousTermElement instanceof TermElementNumber) {
            currentResultValue = currentResultValue.multiply(((TermElementNumber) previousTermElement).getNumbervalue());
        }
    }

    private void devideNumbers() {
        if (previousTermElement instanceof TermElementNumber) {
            currentResultValue = currentResultValue.divide(((TermElementNumber) previousTermElement).getNumbervalue());
        }
    }

    private void parseCharAsPreviousTermElement() throws CalculatorException {
        //TODO: If Clauses vermeiden aber wie?
        if (currentTermPoint.isAWhitespace()) {
            parseWhitespace();
        } else if (currentTermPoint.isANumber()) {
            parseNumber();
        } else if (checkDecimalSeperatorConditions()) {
            parseDigitalSeperator();
        } else if (currentTermPoint.isAOperator()) {
            parseOperator();
        } else {
            throw new CalculatorException();
        }
    }


    private void parseWhitespace() {
        if (previousTermElement instanceof TermElementNumber) {
            ((TermElementNumber) previousTermElement).setPreviousPointAsWhitespace();
        }
    }


    private void parseNumber() throws CalculatorException {
        fillPreviousATermWithNumber();
    }

    private void fillPreviousATermWithNumber() throws CalculatorException {
        if (previousTermElement == null || previousTermElement instanceof TermElementOperator) {
            previousTermElement = new TermElementNumber(currentTermPoint);
        } else if (previousTermElement instanceof TermElementNumber) {
            ((TermElementNumber) previousTermElement).appendDigitToLastPositionOfNumber(currentTermPoint);
        }
    }

    private boolean checkDecimalSeperatorConditions() {
        return currentTermPoint.isADecimalSeparator() && previousTermElement instanceof TermElementNumber;
    }


    private void parseDigitalSeperator() {
        ((TermElementNumber) previousTermElement).setPreviousPointAsSeperator();
    }

    private void parseOperator() throws CalculatorException {
        if (previousTermElement instanceof TermElementOperator) {
            throw new CalculatorException();
        }
        currentOperator = Operator.valueOfSign(String.valueOf(currentTermPoint.token()));
        previousTermElement = new TermElementOperator(currentTermPoint);
    }
}
