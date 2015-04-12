package com.github.lizardev.xquery.saxon.support.trace;

import com.google.common.collect.ImmutableList;
import net.sf.saxon.expr.Container;
import net.sf.saxon.expr.Expression;
import net.sf.saxon.expr.StaticContext;
import net.sf.saxon.expr.flwor.Clause;
import net.sf.saxon.om.StructuredQName;
import net.sf.saxon.trace.TraceCodeInjector;

import java.util.List;

public class TraceCodeInjectorComposite extends TraceCodeInjectorComponent {

    private final List<TraceCodeInjectorComponent> reversedTraceCodeInjectors;

    public TraceCodeInjectorComposite(List<TraceCodeInjectorComponent> traceCodeInjectors) {
        this.reversedTraceCodeInjectors = ImmutableList.copyOf(traceCodeInjectors).reverse();
    }

    @Override
    public Expression inject(Expression expression, StaticContext env, int construct, StructuredQName qName) {
        Expression wrappedExpression = expression;
        int depth = 0;
        for (TraceCodeInjectorComponent reversedTraceCodeInjector : reversedTraceCodeInjectors) {
            wrappedExpression = reversedTraceCodeInjector.inject(wrappedExpression, env, construct, qName, depth++);
        }
        return wrappedExpression;
    }

    @Override
    public Clause injectClause(Clause clause, StaticContext env, Container container) {
        Clause wrappedClause = clause;
        for (TraceCodeInjector traceCodeInjector : reversedTraceCodeInjectors) {
            wrappedClause = traceCodeInjector.injectClause(wrappedClause, env, container);
        }
        return wrappedClause;
    }
}
