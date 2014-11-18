package org.xquery.saxon.coverage.collect;

import org.xquery.saxon.coverage.trace.CoverageInstruction;
import org.xquery.saxon.coverage.trace.InstructionId;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class LineCollector {

    private final int lineNumber;
    private Map<InstructionId, InstructionCollector> instructionCollectors = new LinkedHashMap<>();

    public LineCollector(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public InstructionCollector instructionCreated(CoverageInstruction instruction) {
        return getInstructionCollector(instruction);
    }

    private InstructionCollector getInstructionCollector(CoverageInstruction instruction) {
        InstructionCollector instructionCollector = instructionCollectors.get(instruction.getInstructionId());
        if (instructionCollector == null) {
            instructionCollector = new InstructionCollector(instruction);
            instructionCollectors.put(instruction.getInstructionId(), instructionCollector);
        }
        return instructionCollector;
    }

    public Collection<InstructionCollector> getInstructionCollectors() {
        return instructionCollectors.values();
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
