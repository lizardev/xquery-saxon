package com.github.lizardev.xquery.saxon.support.trace;

import com.github.lizardev.xquery.saxon.support.common.Orderable;
import net.sf.saxon.lib.TraceListener;

import javax.annotation.Nullable;

public interface TraceExtension extends Orderable {

    @Nullable TraceCodeInjectorComponent getTraceCodeInjector();

    @Nullable TraceListener getTraceListener();

    boolean allowsOptimization();
}