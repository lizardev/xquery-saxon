package com.github.lizardev.xquery.saxon.coverage.collect;

import com.github.lizardev.xquery.saxon.coverage.ModuleUri;
import com.github.lizardev.xquery.saxon.coverage.report.InstructionReport;
import com.github.lizardev.xquery.saxon.coverage.report.LineReport;
import com.github.lizardev.xquery.saxon.coverage.report.ModuleReport;
import com.github.lizardev.xquery.saxon.coverage.report.Report;
import com.github.lizardev.xquery.saxon.coverage.trace.*;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.util.Collections.newSetFromMap;

public class DefaultCoverageInstructionEventHandler implements CoverageInstructionEventHandler {

    private final Map<ModuleId, ModuleCollector> modulesCollector = new WeakHashMap<>();
    private final Map<InstructionId, InstructionCollector> instructionCollectors = new WeakHashMap<>();
    private final Set<InstructionId> instructionsOfModuleWithoutUri = newSetFromMap(new WeakHashMap<InstructionId, Boolean>());

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void handle(CoverageInstructionCreatedEvent event) {
        ModuleUri moduleUri = event.getModuleUri();
        lock.writeLock().lock();
        try {
            if (moduleUri == null) {
                instructionsOfModuleWithoutUri.add(event.getInstruction().getInstructionId());
            } else {
                InstructionCollector instructionCollector = getModuleCollector(event.getModuleId(), moduleUri)
                        .instructionCreated(event.getInstruction());
                instructionCollectors.put(event.getInstruction().getInstructionId(), instructionCollector);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void handle(CoverageInstructionInvokedEvent event) {
        lock.readLock().lock();
        try {
            if (!instructionsOfModuleWithoutUri.contains(event.getInstructionId())) {
                instructionCollectors.get(event.getInstructionId()).instructionInvoked();
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void handle(CoverageInstructionSimplifiedEvent event) {
        lock.writeLock().lock();
        try {
            if (!instructionsOfModuleWithoutUri.contains(event.getInstructionId())) {
                InstructionCollector instructionCollector = instructionCollectors.remove(event.getInstructionId());
                instructionCollector.instructionSimplified();
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public synchronized Report getReport() {
        Report report = new Report();
        for (ModuleCollector moduleCollector : modulesCollector.values()) {
            report.addOrMergeModuleReport(getModuleReport(moduleCollector));
        }
        return report;
    }

    private ModuleCollector getModuleCollector(ModuleId moduleId, ModuleUri moduleUri) {
        ModuleCollector moduleCollector = modulesCollector.get(moduleId);
        if (moduleCollector == null) {
            moduleCollector = new ModuleCollector(moduleUri);
            modulesCollector.put(moduleId, moduleCollector);
        }
        return moduleCollector;
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
            if (!instructionCollector.isInstructionSimplified()) {
                instructionReports.add(new InstructionReport(instructionCollector.getInstruction(), instructionCollector.isInstructionInvoked()));
            }
        }
        return new LineReport(lineCollector.getLineNumber(), instructionReports);
    }
}
