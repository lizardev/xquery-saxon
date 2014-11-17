package org.xquery.saxon.adapter.trace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;

import static org.xquery.saxon.adapter.common.OrderableComparator.ORDERABLE_COMPARATOR;

public class SpiTraceExtensionProvider implements TraceExtensionProvider {

    @Override
    public TraceExtension getTraceExtension() {
        ServiceLoader<TraceExtensionProvider> traceExtensionProviders = ServiceLoader.load(TraceExtensionProvider.class);
        List<TraceExtension> traceExtensions = new ArrayList<>();
        for (TraceExtensionProvider traceExtensionProvider : traceExtensionProviders) {
            traceExtensions.add(traceExtensionProvider.getTraceExtension());
        }
        Collections.sort(traceExtensions, ORDERABLE_COMPARATOR);
        return new TraceExtensionComposite(traceExtensions);
    }
}
