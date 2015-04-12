package com.github.lizardev.xquery.saxon.coverage.trace;

import net.sf.saxon.expr.Expression;

public class StableExpressionToStringConverter {
    private static final String HASH_CODE_SEPARATOR = "@";
    private Expression lastExpression;
    private String lastExpressionAsString;

    public String toString(Expression expression) {
        if (lastExpression != expression) {
            lastExpression = expression;
            lastExpressionAsString = convertToString(expression);
        }
        return lastExpressionAsString;
    }

    private String convertToString(Expression expression) {
        String expressionAsString = expression.toString();
        if (startsWithClassName(expressionAsString) && containsHashCodeSuffix(expressionAsString)) {
            expressionAsString = removeHashCodeSuffix(expressionAsString);
        }
        return expressionAsString;
    }

    private String removeHashCodeSuffix(String expressionAsString) {
        return expressionAsString.substring(0, expressionAsString.indexOf(HASH_CODE_SEPARATOR));
    }

    private boolean containsHashCodeSuffix(String expressionAsString) {
        return expressionAsString.contains(HASH_CODE_SEPARATOR);
    }

    private boolean startsWithClassName(String expressionAsString) {
        return expressionAsString.startsWith("com.saxonica") || expressionAsString.startsWith("net.sf.saxon");
    }
}
