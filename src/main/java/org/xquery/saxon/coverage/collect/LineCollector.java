package org.xquery.saxon.coverage.collect;

import org.xquery.saxon.coverage.trace.CoverageExpression;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class LineCollector {

    private final int lineNumber;
    private Map<CoverageExpression, ExpressionCollector> expressionsCollector = new HashMap<CoverageExpression, ExpressionCollector>();

    public LineCollector(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public ExpressionCollector expressionCreated(CoverageExpression expression) {
        return getExpressionCollector(expression);
    }

    private ExpressionCollector getExpressionCollector(CoverageExpression expression) {
        ExpressionCollector expressionCollector = expressionsCollector.get(expression);
        if (expressionCollector == null) {
            expressionCollector = new ExpressionCollector(expression);
            expressionsCollector.put(expression, expressionCollector);
        }
        return expressionCollector;
    }

    public Collection<ExpressionCollector> getExpressionCollectors() {
        return expressionsCollector.values();
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
