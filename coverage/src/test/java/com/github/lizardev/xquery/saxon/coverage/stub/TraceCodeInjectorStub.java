package com.github.lizardev.xquery.saxon.coverage.stub;

import com.github.lizardev.xquery.saxon.support.trace.TraceCodeInjectorComponent;
import net.sf.saxon.expr.Container;
import net.sf.saxon.expr.Expression;
import net.sf.saxon.expr.StaticContext;
import net.sf.saxon.expr.flwor.Clause;
import net.sf.saxon.om.StructuredQName;

public class TraceCodeInjectorStub extends TraceCodeInjectorComponent {
    private int numberOfInjectedExpressions;
    private int numberOfInjectedClauses;

    @Override public Expression inject(Expression exp, StaticContext env, int construct, StructuredQName qName, int depth) {
        numberOfInjectedExpressions++;
        return super.inject(exp, env, construct, qName, depth);
    }

    @Override
    public Clause injectClause(Clause target, StaticContext env, Container container) {
        numberOfInjectedClauses++;
        Clause clause = super.injectClause(target, env, container);
        clause.setLocationId(target.getLocationId());
        return clause;
    }

    public int numberOfInjectedExpressions() {
        return numberOfInjectedExpressions;
    }

    public int numberOfInjectedClauses() {
        return numberOfInjectedClauses;
    }

    public int numberOfInjectedInstructions() {
        return numberOfInjectedExpressions + numberOfInjectedClauses;
    }
}
