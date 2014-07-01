package org.xquery.saxon.coverage.collect;

import org.xquery.saxon.coverage.trace.CoverageExpression;

public class ExpressionCollector {

    private final CoverageExpression expression;
    private boolean expressionInvoked;

    public ExpressionCollector(CoverageExpression expression) {
        this.expression = expression;
    }

    public void expressionInvoked() {
        expressionInvoked = true;
    }

    public boolean isExpressionInvoked() {
        return expressionInvoked;
    }

    public String getExpression() {
        return expression.toString();
    }
}
