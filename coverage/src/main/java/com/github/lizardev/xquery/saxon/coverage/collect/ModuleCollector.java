package com.github.lizardev.xquery.saxon.coverage.collect;

import com.github.lizardev.xquery.saxon.coverage.ModuleUri;
import com.github.lizardev.xquery.saxon.coverage.trace.CoverageInstruction;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class ModuleCollector {

    private ModuleUri moduleUri;
    private Map<Integer, LineCollector> linesCollector = new LinkedHashMap<>();

    public ModuleCollector(ModuleUri moduleUri) {
        this.moduleUri = moduleUri;
    }

    public InstructionCollector instructionCreated(CoverageInstruction instruction) {
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

    public ModuleUri getModuleUri() {
        return moduleUri;
    }
}
