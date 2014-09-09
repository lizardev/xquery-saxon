package org.xquery.saxon.adapter.trace;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.sf.saxon.lib.TraceListener;
import net.sf.saxon.trace.TraceCodeInjector;

import java.util.Collections;
import java.util.List;

public class TraceExtensionComposite implements TraceExtension {

    private final List<TraceExtension> components;

    public TraceExtensionComposite(List<TraceExtension> components) {
        this.components = ImmutableList.copyOf(components);
    }

    @Override
    public TraceCodeInjector getTraceCodeInjector() {
        return new TraceCodeInjectorComposite(Lists.transform(components, new Function<TraceExtension, TraceCodeInjector>() {
            @Override
            public TraceCodeInjector apply(TraceExtension input) {
                return input.getTraceCodeInjector();
            }
        }));
    }

    @Override
    public TraceListener getTraceListener() {
        return new TraceListenerComposite(Lists.transform(components, new Function<TraceExtension, TraceListener>() {
            @Override
            public TraceListener apply(TraceExtension input) {
                return input.getTraceListener();
            }
        }));
    }

    @Override
    public boolean allowsOptimization() {
        return Iterables.all(components, new Predicate<TraceExtension>() {
            @Override
            public boolean apply(TraceExtension input) {
                return input.allowsOptimization();
            }
        });
    }

    @Override
    public int getOrder() {
        return Collections.min(Lists.transform(components, new Function<TraceExtension, Integer>() {
            @Override
            public Integer apply(TraceExtension input) {
                return input.getOrder();
            }
        }));
    }
}
