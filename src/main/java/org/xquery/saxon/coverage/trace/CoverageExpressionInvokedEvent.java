package org.xquery.saxon.coverage.trace;

public class CoverageExpressionInvokedEvent {

    private final CoverageExpression expression;

    public CoverageExpressionInvokedEvent(CoverageExpression expression) {
        this.expression = expression;
    }

    public CoverageExpression getExpression() {
        return expression;
    }
}
