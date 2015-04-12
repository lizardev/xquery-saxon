package com.github.lizardev.xquery.saxon.coverage.stub;

import com.github.lizardev.xquery.saxon.support.trace.TraceCodeInjectorComponent;
import com.github.lizardev.xquery.saxon.support.trace.TraceExtension;
import net.sf.saxon.lib.TraceListener;

public class TraceExtensionStub implements TraceExtension {
    @Override
    public TraceCodeInjectorComponent getTraceCodeInjector() {
        return new TraceCodeInjectorStub();
    }

    @Override
    public TraceListener getTraceListener() {
        return new TraceListenerStub();
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
