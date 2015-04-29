package com.github.lizardev.xquery.saxon.coverage.trace;

import net.sf.saxon.expr.Expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StableExpressionToStringConverter {
    private static final Pattern CLASS_NAME_WITH_HASH_CODE_SUFFIX = Pattern.compile("(com\\.saxonica\\..+?)@[0-9abcdef]+");
    private static final String ZERO_LENGTH_TEXT_NODE_AS_STRING = "ValueOf(\"\"), ";
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
        expressionAsString = removeHashCodeSuffixFromClassName(expressionAsString);
        expressionAsString = removeZeroLengthTextNodeAddedBySaxonAsWorkaround(expressionAsString);
        return expressionAsString;
    }

    private String removeZeroLengthTextNodeAddedBySaxonAsWorkaround(String expression) {
        return expression.replace(ZERO_LENGTH_TEXT_NODE_AS_STRING, "");
    }

    private String removeHashCodeSuffixFromClassName(String expression) {
        Matcher matcher = CLASS_NAME_WITH_HASH_CODE_SUFFIX.matcher(expression);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}