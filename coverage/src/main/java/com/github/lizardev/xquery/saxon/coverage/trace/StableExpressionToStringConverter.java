package com.github.lizardev.xquery.saxon.coverage.trace;

import net.sf.saxon.expr.Expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StableExpressionToStringConverter {
    private static final Pattern CLASS_NAME_WITH_HASH_CODE_SUFFIX = Pattern.compile(".*com\\.saxonica\\..+(@[0-9abcdef]+).*");
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
        Matcher matcher = CLASS_NAME_WITH_HASH_CODE_SUFFIX.matcher(expressionAsString);
        if (matcher.matches()) {
            expressionAsString = expressionAsString.replace(matcher.group(1), "");
        }
        return expressionAsString;
    }
}
