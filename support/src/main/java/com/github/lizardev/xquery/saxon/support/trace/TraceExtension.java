package com.github.lizardev.xquery.saxon.support.trace;

import com.github.lizardev.xquery.saxon.support.common.Orderable;
import net.sf.saxon.lib.TraceListener;
import net.sf.saxon.trace.TraceCodeInjector;

import javax.annotation.Nullable;

public interface TraceExtension extends Orderable {

    @Nullable TraceCodeInjector getTraceCodeInjector();

    @Nullable TraceListener getTraceListener();

    boolean allowsOptimization();
}