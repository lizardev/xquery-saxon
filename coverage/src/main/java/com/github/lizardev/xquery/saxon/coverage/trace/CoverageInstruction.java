package com.github.lizardev.xquery.saxon.coverage.trace;

public interface CoverageInstruction {

    InstructionId getInstructionId();

    int getLineNumber();
}
