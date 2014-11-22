package org.xquery.saxon.adapter.trace;

import javax.annotation.Nullable;

public interface TraceExtensionProvider {

    @Nullable TraceExtension getTraceExtension();
}
