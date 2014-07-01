package org.xquery.saxon.coverage.report;

public class ExpressionReport {

    private final String expression;
    private final boolean covered;

    public ExpressionReport(String expression, boolean covered) {
        this.expression = expression;
        this.covered = covered;
    }

    public boolean isCovered() {
        return covered;
    }

    public String getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "ExpressionReport{" +
                "expression='" + expression + '\'' +
                ", covered=" + covered +
                '}';
    }
}
