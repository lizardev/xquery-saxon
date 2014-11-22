package org.xquery.saxon.adapter.trace;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

import javax.annotation.Nullable;
import java.util.List;
import java.util.ServiceLoader;

import static com.google.common.base.Predicates.notNull;
import static org.xquery.saxon.adapter.common.OrderableComparator.ORDERABLE_COMPARATOR;

public class SpiTraceExtensionProvider implements TraceExtensionProvider {

    @Override
    public TraceExtension getTraceExtension() {
        Function<TraceExtensionProvider, TraceExtension> getTraceException = new Function<TraceExtensionProvider, TraceExtension>() {
            @Nullable @Override public TraceExtension apply(TraceExtensionProvider input) {
                return input.getTraceExtension();
            }
        };
        List<TraceExtension> traceExtensions = FluentIterable.from(ServiceLoader.load(TraceExtensionProvider.class))
                .transform(getTraceException)
                .filter(notNull())
                .toSortedList(ORDERABLE_COMPARATOR);
        if (traceExtensions.isEmpty()) {
            return null;
        } else {
            return new TraceExtensionComposite(traceExtensions);
        }
    }
}
