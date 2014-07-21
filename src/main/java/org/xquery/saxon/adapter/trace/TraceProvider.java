package org.xquery.saxon.adapter.trace;

import net.sf.saxon.lib.TraceListener;
import net.sf.saxon.trace.TraceCodeInjector;

public interface TraceProvider {

    TraceCodeInjector getTraceCodeInjector();

    TraceListener getTraceListener();

    boolean supportsOptimization();
}
