package com.github.lizardev.xquery.saxon.support.trace;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.sf.saxon.lib.TraceListener;
import net.sf.saxon.trace.TraceCodeInjector;

import java.util.Collections;
import java.util.List;

import static com.google.common.base.Predicates.notNull;

public class TraceExtensionComposite implements TraceExtension {

    private final List<TraceExtension> components;

    public TraceExtensionComposite(List<TraceExtension> components) {
        this.components = ImmutableList.copyOf(components);
    }

    @Override
    public Optional<TraceCodeInjector> getTraceCodeInjector() {
        Function<TraceExtension, TraceCodeInjector> getTraceCodeInjector = new Function<TraceExtension, TraceCodeInjector>() {
            @Override
            public TraceCodeInjector apply(TraceExtension input) {
                return input.getTraceCodeInjector().get();
            }
        };
        List<TraceCodeInjector> traceCodeInjectors = FluentIterable
                .from(components)
                .transform(getTraceCodeInjector)
                .filter(notNull())
                .toList();
        if (traceCodeInjectors.isEmpty()) {
            return Optional.absent();
        } else {
            return Optional.of((TraceCodeInjector) new TraceCodeInjectorComposite(traceCodeInjectors));
        }

    }

    @Override
    public Optional<TraceListener> getTraceListener() {
        Function<TraceExtension, TraceListener> getTraceListener = new Function<TraceExtension, TraceListener>() {
            @Override
            public TraceListener apply(TraceExtension input) {
                return input.getTraceListener().get();
            }
        };
        List<TraceListener> traceListeners = FluentIterable
                .from(components)
                .transform(getTraceListener)
                .filter(notNull())
                .toList();
        if (traceListeners.isEmpty()) {
            return Optional.absent();
        } else {
            return Optional.of((TraceListener) new TraceListenerComposite(traceListeners));
        }
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