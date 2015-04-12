package com.github.lizardev.xquery.saxon.support.trace;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.sf.saxon.lib.TraceListener;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Predicates.notNull;

public class TraceExtensionComposite implements TraceExtension {

    private final List<TraceExtension> components;

    public TraceExtensionComposite(List<TraceExtension> components) {
        this.components = ImmutableList.copyOf(components);
    }

    public TraceExtensionComposite(TraceExtension... components) {
        this(Arrays.asList(components));
    }

    @Nullable @Override
    public TraceCodeInjectorComponent getTraceCodeInjector() {
        Function<TraceExtension, TraceCodeInjectorComponent> getTraceCodeInjector = new Function<TraceExtension, TraceCodeInjectorComponent>() {
            @Nullable @Override
            public TraceCodeInjectorComponent apply(TraceExtension input) {
                return input.getTraceCodeInjector();
            }
        };
        List<TraceCodeInjectorComponent> traceCodeInjectors = FluentIterable
                .from(components)
                .transform(getTraceCodeInjector)
                .filter(notNull())
                .toList();
        if (traceCodeInjectors.isEmpty()) {
            return null;
        } else {
            return new TraceCodeInjectorComposite(traceCodeInjectors);
        }

    }

    @Nullable @Override
    public TraceListener getTraceListener() {
        Function<TraceExtension, TraceListener> getTraceListener = new Function<TraceExtension, TraceListener>() {
            @Override
            public TraceListener apply(TraceExtension input) {
                return input.getTraceListener();
            }
        };
        List<TraceListener> traceListeners = FluentIterable
                .from(components)
                .transform(getTraceListener)
                .filter(notNull())
                .toList();
        if (traceListeners.isEmpty()) {
            return null;
        } else {
            return new TraceListenerComposite(traceListeners);
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