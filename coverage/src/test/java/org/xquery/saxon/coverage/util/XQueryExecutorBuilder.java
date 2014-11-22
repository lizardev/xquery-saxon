package org.xquery.saxon.coverage.util;

import org.xquery.saxon.support.trace.TraceExtension;

public final class XQueryExecutorBuilder {

    private TraceExtension traceExtension;

    private XQueryExecutorBuilder() {
    }

    public static XQueryExecutorBuilder xQueryExecutor() {
        return new XQueryExecutorBuilder();
    }

    public XQueryExecutorBuilder withTraceExtension(TraceExtension traceExtension) {
        this.traceExtension = traceExtension;
        return this;
    }

    public XQueryExecutor build() {
        return new XQueryExecutor(traceExtension);
    }
}
