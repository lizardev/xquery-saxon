package com.github.lizardev.xquery.saxon.coverage.trace;

import java.io.Serializable;

public class CoverageInstruction implements Serializable {

    private static final long serialVersionUID = 1;

    private final InstructionId instructionId;
    private final String instruction;
    private final int lineNumber;

    public CoverageInstruction(InstructionId instructionId, String instruction, int lineNumber) {
        this.instructionId = instructionId;
        this.instruction = instruction;
        this.lineNumber = lineNumber;
    }

    public InstructionId getInstructionId() {
        return instructionId;
    }

    public String getInstruction() {
        return instruction;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
