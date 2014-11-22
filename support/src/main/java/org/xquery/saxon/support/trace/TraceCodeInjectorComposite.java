package org.xquery.saxon.support.trace;

import com.google.common.collect.ImmutableList;
import net.sf.saxon.expr.Container;
import net.sf.saxon.expr.Expression;
import net.sf.saxon.expr.StaticContext;
import net.sf.saxon.expr.flwor.Clause;
import net.sf.saxon.om.StructuredQName;
import net.sf.saxon.trace.TraceCodeInjector;

import java.util.List;

public class TraceCodeInjectorComposite extends TraceCodeInjector {

    private final List<TraceCodeInjector> reversedTraceCodeInjectors;

    public TraceCodeInjectorComposite(List<TraceCodeInjector> traceCodeInjectors) {
        this.reversedTraceCodeInjectors = ImmutableList.copyOf(traceCodeInjectors).reverse();
    }

    @Override
    public Expression inject(Expression expression, StaticContext env, int construct, StructuredQName qName) {
        Expression wrappedExpression = expression;
        for (TraceCodeInjector traceCodeInjector : reversedTraceCodeInjectors) {
            wrappedExpression = traceCodeInjector.inject(wrappedExpression, env, construct, qName);
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
