package org.xquery.saxon.coverage.collect;

import org.xquery.saxon.coverage.trace.CoverageExpression;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class LineCollector {

    private final int lineNumber;
    private Map<CoverageExpression, InstructionCollector> instructionCollector = new HashMap<CoverageExpression, InstructionCollector>();

    public LineCollector(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public InstructionCollector instructionCreated(CoverageExpression instruction) {
        return getInstructionCollector(instruction);
    }

    private InstructionCollector getInstructionCollector(CoverageExpression instruction) {
        InstructionCollector instructionCollector = this.instructionCollector.get(instruction);
        if (instructionCollector == null) {
            instructionCollector = new InstructionCollector(instruction);
            this.instructionCollector.put(instruction, instructionCollector);
        }
        return instructionCollector;
    }

    public Collection<InstructionCollector> getInstructionCollectors() {
        return instructionCollector.values();
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
