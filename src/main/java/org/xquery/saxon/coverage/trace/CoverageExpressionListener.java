package org.xquery.saxon.coverage.trace;

public class CoverageExpressionListener extends AbstractCoverageExpressionListener {

    private final CoverageExpressionEventHandler eventHandler;

    public CoverageExpressionListener(CoverageExpressionEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    protected void enter(CoverageExpression expression) {
        eventHandler.handle(new CoverageExpressionInvokedEvent(expression));
    }
}
