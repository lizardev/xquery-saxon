package com.github.lizardev.xquery.saxon.coverage.trace;

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
    public Expression inject(Expression expression, StaticContext env, int construct, StructuredQName qName) {
        CoverageExpression coverageExpression = new CoverageExpression(expression);
        coverageExpression.setNamespaceResolver(env.getNamespaceResolver());
        coverageExpression.setConstructType(construct);
        coverageExpression.setObjectName(qName);
        eventHandler.handle(new CoverageInstructionCreatedEvent(coverageExpression, (QueryModule) env));
        return coverageExpression;
    }

    @Override
    public Clause injectClause(Clause clause, StaticContext env, Container container) {
        CoverageClause coverageClause = new CoverageClause(clause, env.getNamespaceResolver(), container);
        eventHandler.handle(new CoverageInstructionCreatedEvent(coverageClause, (QueryModule) env));
        return coverageClause;
    }

}
