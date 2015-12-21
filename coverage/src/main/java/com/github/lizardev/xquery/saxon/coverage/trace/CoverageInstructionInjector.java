package com.github.lizardev.xquery.saxon.coverage.trace;

import com.github.lizardev.xquery.saxon.coverage.ModuleUri;
import com.github.lizardev.xquery.saxon.coverage.collect.ModuleId;
import com.github.lizardev.xquery.saxon.support.trace.TraceCodeInjectorComponent;
import com.google.common.cache.Cache;
import net.sf.saxon.expr.Container;
import net.sf.saxon.expr.Expression;
import net.sf.saxon.expr.StaticContext;
import net.sf.saxon.expr.flwor.Clause;
import net.sf.saxon.om.StructuredQName;
import net.sf.saxon.query.QueryModule;

import java.util.concurrent.ConcurrentMap;

import static com.github.lizardev.xquery.saxon.coverage.collect.ModuleId.uniqueModuleId;
import static com.google.common.cache.CacheBuilder.newBuilder;

public class CoverageInstructionInjector extends TraceCodeInjectorComponent {

    private final ConcurrentMap<QueryModule, ModuleId> moduleIds;
    private final CoverageInstructionEventHandler eventHandler;

    public CoverageInstructionInjector(CoverageInstructionEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        Cache<QueryModule, ModuleId> cache = newBuilder().weakKeys().weakValues().build();
        moduleIds = cache.asMap();
    }

    @Override
    public Expression inject(Expression expression, StaticContext env, int construct, StructuredQName qName) {
        return inject(expression, env, construct, qName, 0);
    }

    @Override
    public Expression inject(Expression expression, StaticContext env, int construct, StructuredQName qName, int depth) {
        CoverageExpression coverageExpression = new CoverageExpression(expression, depth, eventHandler);
        coverageExpression.setNamespaceResolver(env.getNamespaceResolver());
        coverageExpression.setConstructType(construct);
        coverageExpression.setObjectName(qName);
        CoverageInstruction coverageInstruction = new CoverageInstruction(coverageExpression.getInstructionId(), coverageExpression.toString(), expression.getLineNumber());
        eventHandler.handle(createCoverageInstructionCreatedEvent((QueryModule) env, coverageInstruction));
        return coverageExpression;
    }

    @Override
    public Clause injectClause(Clause clause, StaticContext env, Container container) {
        CoverageClause coverageClause = new CoverageClause(clause, env.getNamespaceResolver(), container);
        CoverageInstruction coverageInstruction = new CoverageInstruction(coverageClause.getInstructionId(), coverageClause.toString(), clause.getLocationId());
        eventHandler.handle(createCoverageInstructionCreatedEvent((QueryModule) env, coverageInstruction));
        return coverageClause;
    }

    private CoverageInstructionCreatedEvent createCoverageInstructionCreatedEvent(QueryModule queryModule, CoverageInstruction coverageInstruction) {
        ModuleId moduleId = uniqueModuleId();
        ModuleId existingModuleId = moduleIds.putIfAbsent(queryModule, moduleId);
        if (existingModuleId != null) {
            moduleId = existingModuleId;
        }
        ModuleUri moduleUri = queryModule.getLocationURI() == null ? null : ModuleUri.fromUri(queryModule.getLocationURI());
        return new CoverageInstructionCreatedEvent(moduleId, moduleUri, coverageInstruction);
    }
}