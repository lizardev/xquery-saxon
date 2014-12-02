package com.github.lizardev.xquery.saxon.coverage.collect;

import net.sf.saxon.query.QueryModule;
import com.github.lizardev.xquery.saxon.coverage.ModuleUri;
import com.github.lizardev.xquery.saxon.coverage.report.InstructionReport;
import com.github.lizardev.xquery.saxon.coverage.report.LineReport;
import com.github.lizardev.xquery.saxon.coverage.report.ModuleReport;
import com.github.lizardev.xquery.saxon.coverage.report.Report;
import com.github.lizardev.xquery.saxon.coverage.trace.CoverageInstructionCreatedEvent;
import com.github.lizardev.xquery.saxon.coverage.trace.CoverageInstructionEventHandler;
import com.github.lizardev.xquery.saxon.coverage.trace.CoverageInstructionInvokedEvent;
import com.github.lizardev.xquery.saxon.coverage.trace.InstructionId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import static com.github.lizardev.xquery.saxon.coverage.collect.ModuleId.uniqueModuleId;
import static com.github.lizardev.xquery.saxon.coverage.util.MapUtils.putIfAbsent;

public class DefaultCoverageInstructionEventHandler implements CoverageInstructionEventHandler {

    private Map<QueryModule, ModuleId> moduleIds = new WeakHashMap<>();
    private Map<ModuleId, ModuleCollector> modulesCollector = new HashMap<>();
    private Map<InstructionId, InstructionCollector> instructionCollectors = new HashMap<>();
    private Set<InstructionId> instructionsOfModuleWithoutUri = new HashSet<>();

    public synchronized void handle(CoverageInstructionCreatedEvent event) {
        ModuleUri moduleUri = getModuleUri(event.getQueryModule());
        if (moduleUri == null) {
            instructionsOfModuleWithoutUri.add(event.getInstruction().getInstructionId());
        } else {
            InstructionCollector instructionCollector = getModuleCollector(event.getQueryModule(), moduleUri)
                    .instructionCreated(event.getInstruction());
            instructionCollectors.put(event.getInstruction().getInstructionId(), instructionCollector);
        }
    }

    public synchronized void handle(CoverageInstructionInvokedEvent event) {
        if (!instructionsOfModuleWithoutUri.contains(event.getInstructionId())) {
            instructionCollectors.get(event.getInstructionId()).instructionInvoked();
        }
    }

    public synchronized Report getReport() {
        Report report = new Report();
        for (ModuleCollector moduleCollector : modulesCollector.values()) {
            report.addOrMergeModuleReport(getModuleReport(moduleCollector));
        }
        return report;
    }

    private ModuleUri getModuleUri(QueryModule queryModule) {
        if (queryModule.getLocationURI() == null) {
            return null;
        } else {
            return ModuleUri.fromUri(queryModule.getLocationURI());
        }
    }

    private ModuleCollector getModuleCollector(QueryModule queryModule, ModuleUri moduleUri) {
        ModuleId moduleId = getModuleId(queryModule);
        ModuleCollector moduleCollector = modulesCollector.get(moduleId);
        if (moduleCollector == null) {
            moduleCollector = new ModuleCollector(moduleUri);
            modulesCollector.put(moduleId, moduleCollector);
        }
        return moduleCollector;
    }

    private ModuleId getModuleId(QueryModule queryModule) {
        return putIfAbsent(moduleIds, queryModule, uniqueModuleId());
    }

    private ModuleReport getModuleReport(ModuleCollector moduleCollector) {
        List<LineReport> lineReports = new ArrayList<>();
        for (LineCollector lineCollector : moduleCollector.getLineCollectors()) {
            lineReports.add(getLineReport(lineCollector));
        }
        return new ModuleReport(moduleCollector.getModuleUri(), lineReports);
    }

    private LineReport getLineReport(LineCollector lineCollector) {
        List<InstructionReport> instructionReports = new ArrayList<>(lineCollector.getInstructionCollectors().size());
        for (InstructionCollector instructionCollector : lineCollector.getInstructionCollectors()) {
            instructionReports.add(new InstructionReport(instructionCollector.getInstruction(), instructionCollector.isInstructionInvoked()));
        }
        return new LineReport(lineCollector.getLineNumber(), instructionReports);
    }
}
