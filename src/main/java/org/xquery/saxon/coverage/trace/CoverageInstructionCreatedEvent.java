package org.xquery.saxon.coverage.trace;

import net.sf.saxon.query.QueryModule;

public class CoverageInstructionCreatedEvent {

    private final CoverageInstruction instruction;
    private final QueryModule queryModule;

    public CoverageInstructionCreatedEvent(CoverageInstruction instruction, QueryModule queryModule) {
        this.instruction = instruction;
        this.queryModule = queryModule;
    }

    public CoverageInstruction getInstruction() {
        return instruction;
    }

    public QueryModule getQueryModule() {
        return queryModule;
    }
}
