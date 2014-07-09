package org.xquery.saxon.coverage.trace;

import net.sf.saxon.expr.Container;
import net.sf.saxon.expr.Expression;
import net.sf.saxon.expr.StaticContext;
import net.sf.saxon.expr.flwor.Clause;
import net.sf.saxon.om.StructuredQName;
import net.sf.saxon.query.QueryModule;
import net.sf.saxon.trace.TraceCodeInjector;

public class CoverageInstructionInjector extends TraceCodeInjector {

    private final CoverageInstructionEventHandler eventHandler;

    public CoverageInstructionInjector(CoverageInstructionEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public Expression inject(Expression exp, StaticContext env, int construct, StructuredQName qName) {
        CoverageExpression coverageExpression = new CoverageExpression(exp);
        coverageExpression.setNamespaceResolver(env.getNamespaceResolver());
        coverageExpression.setConstructType(construct);
        coverageExpression.setObjectName(qName);
        eventHandler.handle(new CoverageInstructionCreatedEvent(coverageExpression, (QueryModule) env));
        return coverageExpression;
    }

    @Override
    public Clause injectClause(Clause target, StaticContext env, Container container) {
        return target;
    }
}
