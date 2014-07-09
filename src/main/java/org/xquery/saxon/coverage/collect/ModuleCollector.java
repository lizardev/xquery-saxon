package org.xquery.saxon.coverage.collect;

import org.xquery.saxon.coverage.trace.CoverageExpression;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ModuleCollector {

    private String module;
    private Map<Integer, LineCollector> linesCollector = new HashMap<Integer, LineCollector>();

    public ModuleCollector(String module) {
        this.module = module;
    }

    public InstructionCollector instructionCreated(CoverageExpression instruction) {
        return getLineCollector(instruction.getLineNumber()).instructionCreated(instruction);
    }

    private LineCollector getLineCollector(int lineNumber) {
        LineCollector lineCollector = linesCollector.get(lineNumber);
        if (lineCollector == null) {
            lineCollector = new LineCollector(lineNumber);
            linesCollector.put(lineNumber, lineCollector);
        }
        return lineCollector;
    }

    public Collection<LineCollector> getLineCollectors() {
        return linesCollector.values();
    }

    public String getModule() {
        return module;
    }
}
