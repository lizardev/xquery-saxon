package com.github.lizardev.xquery.saxon.coverage.stub;

import com.github.lizardev.xquery.saxon.support.trace.TraceCodeInjectorComponent;
import net.sf.saxon.expr.Container;
import net.sf.saxon.expr.StaticContext;
import net.sf.saxon.expr.flwor.Clause;

public class TraceCodeInjectorStub extends TraceCodeInjectorComponent {
    @Override
    public Clause injectClause(Clause target, StaticContext env, Container container) {
        Clause clause = super.injectClause(target, env, container);
        clause.setLocationId(target.getLocationId());
        return clause;
    }
}
