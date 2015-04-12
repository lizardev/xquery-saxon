package com.github.lizardev.xquery.saxon.coverage.trace;

import com.github.lizardev.xquery.saxon.coverage.ModuleUri;
import com.github.lizardev.xquery.saxon.coverage.collect.ModuleId;
import com.github.lizardev.xquery.saxon.coverage.util.MapUtils;
import com.github.lizardev.xquery.saxon.support.trace.TraceCodeInjectorComponent;
import net.sf.saxon.expr.Container;
import net.sf.saxon.expr.Expression;
import net.sf.saxon.expr.StaticContext;
import net.sf.saxon.expr.flwor.Clause;
import net.sf.saxon.om.StructuredQName;
import net.sf.saxon.query.QueryModule;

import java.util.Map;
import java.util.WeakHashMap;

import static com.github.lizardev.xquery.saxon.coverage.collect.ModuleId.uniqueModuleId;

public class CoverageInstructionInjector extends TraceCodeInjectorComponent {

    private final Map<QueryModule, ModuleId> moduleIds = new WeakHashMap<>();
    private final CoverageInstructionEventHandler eventHandler;

    public CoverageInstructionInjector(CoverageInstructionEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public synchronized Expression inject(Expression expression, StaticContext env, int construct, StructuredQName qName) {
        return inject(expression, env, construct, qName, 0);
    }

    @Override
    public synchronized Expression inject(Expression expression, StaticContext env, int construct, StructuredQName qName, int depth) {
        CoverageExpression coverageExpression = new CoverageExpression(expression, depth, eventHandler);
        coverageExpression.setNamespaceResolver(env.getNamespaceResolver());
        coverageExpression.setConstructType(construct);
        coverageExpression.setObjectName(qName);
        CoverageInstruction coverageInstruction = new CoverageInstruction(coverageExpression.getInstructionId(), coverageExpression.toString(), expression.getLineNumber());
        eventHandler.handle(createCoverageInstructionCreatedEvent((QueryModule) env, coverageInstruction));
        return coverageExpression;
    }

    @Override
    public synchronized Clause injectClause(Clause clause, StaticContext env, Container container) {
        CoverageClause coverageClause = new CoverageClause(clause, env.getNamespaceResolver(), container);
        CoverageInstruction coverageInstruction = new CoverageInstruction(coverageClause.getInstructionId(), coverageClause.toString(), clause.getLocationId());
        eventHandler.handle(createCoverageInstructionCreatedEvent((QueryModule) env, coverageInstruction));
        return coverageClause;
    }

    private CoverageInstructionCreatedEvent createCoverageInstructionCreatedEvent(QueryModule queryModule, CoverageInstruction coverageInstruction) {
        ModuleId moduleId = MapUtils.putIfAbsent(moduleIds, queryModule, uniqueModuleId());
        ModuleUri moduleUri = queryModule.getLocationURI() == null ? null : ModuleUri.fromUri(queryModule.getLocationURI());
        return new CoverageInstructionCreatedEvent(moduleId, moduleUri, coverageInstruction);
    }
}