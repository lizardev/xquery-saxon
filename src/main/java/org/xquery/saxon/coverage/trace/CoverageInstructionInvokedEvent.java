package org.xquery.saxon.coverage.trace;

public class CoverageInstructionInvokedEvent {

    private final CoverageExpression instruction;

    public CoverageInstructionInvokedEvent(CoverageExpression instruction) {
        this.instruction = instruction;
    }

    public CoverageExpression getInstruction() {
        return instruction;
    }
}
