package org.xquery.saxon.coverage.collect;

import org.xquery.saxon.coverage.trace.CoverageInstruction;
import org.xquery.saxon.coverage.trace.Identifier;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class LineCollector {

    private final int lineNumber;
    private Map<Identifier, InstructionCollector> instructionCollectors = new HashMap<>();

    public LineCollector(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public InstructionCollector instructionCreated(CoverageInstruction instruction) {
        return getInstructionCollector(instruction);
    }

    private InstructionCollector getInstructionCollector(CoverageInstruction instruction) {
        InstructionCollector instructionCollector = instructionCollectors.get(instruction.getIdentifier());
        if (instructionCollector == null) {
            instructionCollector = new InstructionCollector(instruction);
			instructionCollectors.put(instruction.getIdentifier(), instructionCollector);
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
