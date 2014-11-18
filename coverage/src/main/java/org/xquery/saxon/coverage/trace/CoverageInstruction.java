package org.xquery.saxon.coverage.trace;

public interface CoverageInstruction {

    InstructionId getInstructionId();

    int getLineNumber();
}
