package com.github.lizardev.xquery.saxon.support.trace;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

import javax.annotation.Nullable;
import java.util.List;
import java.util.ServiceLoader;

import static com.github.lizardev.xquery.saxon.support.common.OrderableComparator.ORDERABLE_COMPARATOR;
import static com.google.common.base.Predicates.notNull;

public class SpiTraceExtensionProvider implements TraceExtensionProvider {

    public static final SpiTraceExtensionProvider INSTANCE = new SpiTraceExtensionProvider();
    private volatile boolean initialized;
    private TraceExtension traceExtension;

    @Override
    public TraceExtension getTraceExtension() {
        if (!initialized) {
            synchronized (this) {
                traceExtension = createTraceExtension();
                initialized = true;
            }
        }
        return traceExtension;
    }

    private TraceExtension createTraceExtension() {
        Function<TraceExtensionProvider, TraceExtension> getTraceExtension = new Function<TraceExtensionProvider, TraceExtension>() {
            @Nullable @Override
            public TraceExtension apply(TraceExtensionProvider input) {
                return input.getTraceExtension();
            }
        };
        List<TraceExtension> traceExtensions = FluentIterable.from(ServiceLoader.load(TraceExtensionProvider.class))
                .transform(getTraceExtension)
                .filter(notNull())
                .toSortedList(ORDERABLE_COMPARATOR);
        if (traceExtensions.isEmpty()) {
            return null;
        } else {
            return new TraceExtensionComposite(traceExtensions);
        }
    }
}
