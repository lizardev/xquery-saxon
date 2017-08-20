package com.github.lizardev.xquery.saxon.coverage.report;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class InstructionReport implements Serializable {

    private static final long serialVersionUID = 1;
    private final String instruction;
    private final boolean covered;

    public InstructionReport(String instruction, boolean covered) {
        this.instruction = instruction;
        this.covered = covered;
    }

    public boolean isCovered() {
        return covered;
    }

    public String getInstruction() {
        return instruction;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE).append("instruction", instruction).append("covered", covered).build();
    }

    public InstructionReport merge(InstructionReport instructionReport) {
        checkArgument(instruction.equals(instructionReport.instruction), "instructions must be the same");
        return new InstructionReport(instruction, covered || instructionReport.covered);
    }
}
