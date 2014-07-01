package org.xquery.saxon.coverage.trace;

public interface CoverageExpressionEventHandler {

    void handle(CoverageExpressionCreatedEvent event);

    void handle(CoverageExpressionInvokedEvent event);
}
