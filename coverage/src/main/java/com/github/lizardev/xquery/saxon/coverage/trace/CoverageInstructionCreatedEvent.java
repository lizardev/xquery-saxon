package com.github.lizardev.xquery.saxon.coverage.trace;

import com.github.lizardev.xquery.saxon.coverage.ModuleUri;
import com.github.lizardev.xquery.saxon.coverage.collect.ModuleId;

public class CoverageInstructionCreatedEvent {

    private final ModuleId moduleId;
    private final ModuleUri moduleUri;
    private final CoverageInstruction coverageInstruction;

    public CoverageInstructionCreatedEvent(ModuleId moduleId, ModuleUri moduleUri, CoverageInstruction coverageInstruction) {
        this.moduleId = moduleId;
        this.moduleUri = moduleUri;
        this.coverageInstruction = coverageInstruction;
    }

    public ModuleId getModuleId() {
        return moduleId;
    }

    public ModuleUri getModuleUri() {
        return moduleUri;
    }

    public CoverageInstruction getInstruction() {
        return coverageInstruction;
    }
}
