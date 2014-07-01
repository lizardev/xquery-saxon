package org.xquery.saxon.coverage.trace;

import net.sf.saxon.query.QueryModule;

public class CoverageExpressionCreatedEvent {

    private final CoverageExpression expression;
    private final QueryModule queryModule;

    public CoverageExpressionCreatedEvent(CoverageExpression expression, QueryModule queryModule) {
        this.expression = expression;
        this.queryModule = queryModule;
    }

    public CoverageExpression getExpression() {
        return expression;
    }

    public QueryModule getQueryModule() {
        return queryModule;
    }
}
