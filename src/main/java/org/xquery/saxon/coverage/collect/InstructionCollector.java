package org.xquery.saxon.coverage.collect;

import org.xquery.saxon.coverage.trace.CoverageExpression;

public class InstructionCollector {

    private final CoverageExpression instruction;
    private boolean instructionInvoked;

    public InstructionCollector(CoverageExpression instruction) {
        this.instruction = instruction;
    }

    public void instructionInvoked() {
		instructionInvoked = true;
    }

    public boolean isInstructionInvoked() {
        return instructionInvoked;
    }

    public String getInstruction() {
        return instruction.toString();
    }
}
