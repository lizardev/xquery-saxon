package com.github.lizardev.xquery.saxon.support.trace;

import com.github.lizardev.xquery.saxon.support.common.Orderable;
import com.google.common.base.Optional;
import net.sf.saxon.lib.TraceListener;
import net.sf.saxon.trace.TraceCodeInjector;

public interface TraceExtension extends Orderable {

    Optional<TraceCodeInjector> getTraceCodeInjector();

    Optional<TraceListener> getTraceListener();

    boolean allowsOptimization();
}