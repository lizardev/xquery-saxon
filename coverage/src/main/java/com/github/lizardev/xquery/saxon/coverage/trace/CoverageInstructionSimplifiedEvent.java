package com.github.lizardev.xquery.saxon.coverage.trace;

public class CoverageInstructionSimplifiedEvent {
    private final InstructionId instructionId;

    public CoverageInstructionSimplifiedEvent(InstructionId instructionId) {
        this.instructionId = instructionId;
    }

    public InstructionId getInstructionId() {
        return instructionId;
    }
}
