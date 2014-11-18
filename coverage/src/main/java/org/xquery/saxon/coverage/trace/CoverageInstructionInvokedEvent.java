package org.xquery.saxon.coverage.trace;

public class CoverageInstructionInvokedEvent {

    private InstructionId instructionId;

    public CoverageInstructionInvokedEvent(InstructionId instructionId) {
        this.instructionId = instructionId;
    }

    public InstructionId getInstructionId() {
        return instructionId;
    }
}
