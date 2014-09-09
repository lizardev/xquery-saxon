package org.xquery.saxon.adapter.trace;

import net.sf.saxon.lib.TraceListener;
import net.sf.saxon.trace.TraceCodeInjector;
import org.xquery.saxon.adapter.common.Orderable;

public interface TraceExtension extends Orderable {

    TraceCodeInjector getTraceCodeInjector();

    TraceListener getTraceListener();

    boolean allowsOptimization();
}