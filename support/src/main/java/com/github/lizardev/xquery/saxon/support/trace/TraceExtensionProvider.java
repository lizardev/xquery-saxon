package com.github.lizardev.xquery.saxon.support.trace;


import com.google.common.base.Optional;

public interface TraceExtensionProvider {

    Optional<TraceExtension> getTraceExtension();
}
