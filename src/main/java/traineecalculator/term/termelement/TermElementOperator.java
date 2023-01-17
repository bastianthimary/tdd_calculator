package traineecalculator.term.termelement;

import traineecalculator.CalculatorException;
import traineecalculator.term.Operator;
import traineecalculator.term.termpoint.TermPoint;

public class TermElementOperator extends TermElement {
    private final Operator operator;

    public TermElementOperator(TermPoint point) throws CalculatorException {
        super();
        stringValue = String.valueOf(point.getToken());
        if (point.isAOperator()) {
            operator = Operator.valueOfSign(stringValue);
        } else {
            throw new CalculatorException();
        }
    }

    public Operator getOperator() {
        return operator;
    }

}
