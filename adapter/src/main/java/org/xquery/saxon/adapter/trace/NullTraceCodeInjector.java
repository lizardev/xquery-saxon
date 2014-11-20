package org.xquery.saxon.adapter.trace;

import net.sf.saxon.expr.Container;
import net.sf.saxon.expr.Expression;
import net.sf.saxon.expr.StaticContext;
import net.sf.saxon.expr.flwor.Clause;
import net.sf.saxon.om.StructuredQName;
import net.sf.saxon.trace.TraceCodeInjector;

public final class NullTraceCodeInjector extends TraceCodeInjector {

    @Override
    public Expression inject(Expression exp, StaticContext env, int construct, StructuredQName qName) {
        return exp;
    }

    @Override
    public Clause injectClause(Clause target, StaticContext env, Container container) {
        return target;
    }
}
