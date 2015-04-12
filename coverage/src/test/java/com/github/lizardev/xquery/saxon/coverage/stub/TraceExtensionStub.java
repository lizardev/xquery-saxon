package com.github.lizardev.xquery.saxon.coverage.stub;

import com.github.lizardev.xquery.saxon.support.trace.TraceExtension;

public class TraceExtensionStub implements TraceExtension {
    private final TraceCodeInjectorStub traceCodeInjector = new TraceCodeInjectorStub();
    private final TraceListenerStub traceListenerStub = new TraceListenerStub();

    @Override
    public TraceCodeInjectorStub getTraceCodeInjector() {
        return traceCodeInjector;
    }

    @Override
    public TraceListenerStub getTraceListener() {
        return traceListenerStub;
    }

    @Override
    public boolean allowsOptimization() {
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
