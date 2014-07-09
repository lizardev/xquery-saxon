package org.xquery.saxon.coverage.trace;

import net.sf.saxon.query.QueryModule;

public class CoverageInstructionCreatedEvent {

    private final CoverageExpression instruction;
    private final QueryModule queryModule;

    public CoverageInstructionCreatedEvent(CoverageExpression instruction, QueryModule queryModule) {
        this.instruction = instruction;
        this.queryModule = queryModule;
    }

    public CoverageExpression getInstruction() {
        return instruction;
    }

    public QueryModule getQueryModule() {
        return queryModule;
    }
}
