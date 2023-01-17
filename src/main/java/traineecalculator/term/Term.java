package traineecalculator.term;

import traineecalculator.term.termpoint.TermPoint;

import java.util.ArrayList;
import java.util.List;

public class Term {
    private final String term;

    public Term(String term) {
        this.term = term;
    }

    public List<TermPoint> getTermPoints() {
        List<TermPoint> pointer = new ArrayList<>();
        for (char point : term.toCharArray()) {
            pointer.add(new TermPoint(point));
        }
        return pointer;
    }


}
