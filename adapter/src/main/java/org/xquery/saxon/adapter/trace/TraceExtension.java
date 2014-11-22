package org.xquery.saxon.adapter.trace;

import net.sf.saxon.lib.TraceListener;
import net.sf.saxon.trace.TraceCodeInjector;
import org.xquery.saxon.adapter.common.Orderable;

import javax.annotation.Nullable;

public interface TraceExtension extends Orderable {

    @Nullable TraceCodeInjector getTraceCodeInjector();

    @Nullable TraceListener getTraceListener();

    boolean allowsOptimization();
}