package org.xquery.saxon.coverage.collect;

import org.xquery.saxon.coverage.report.InstructionReport;
import org.xquery.saxon.coverage.report.LineReport;
import org.xquery.saxon.coverage.report.ModuleReport;
import org.xquery.saxon.coverage.report.Report;
import org.xquery.saxon.coverage.trace.CoverageExpression;
import org.xquery.saxon.coverage.trace.CoverageInstructionCreatedEvent;
import org.xquery.saxon.coverage.trace.CoverageInstructionEventHandler;
import org.xquery.saxon.coverage.trace.CoverageInstructionInvokedEvent;

import java.util.HashMap;
import java.util.Map;

public class DefaultCoverageInstructionEventHandler implements CoverageInstructionEventHandler {

    private Map<String, ModuleCollector> modulesCollector = new HashMap<String, ModuleCollector>();
    private Map<CoverageExpression, InstructionCollector> instructionCollector = new HashMap<CoverageExpression, InstructionCollector>();

    public void handle(CoverageInstructionCreatedEvent event) {
        InstructionCollector instructionCollector = getModuleCollector(event.getQueryModule().getSystemId())
                .instructionCreated(event.getInstruction());
        this.instructionCollector.put(event.getInstruction(), instructionCollector);
    }

    private ModuleCollector getModuleCollector(String module) {
        ModuleCollector moduleCollector = modulesCollector.get(module);
        if (moduleCollector == null) {
            moduleCollector = new ModuleCollector(module);
            modulesCollector.put(module, moduleCollector);
        }
        return moduleCollector;
    }

    public void handle(CoverageInstructionInvokedEvent event) {
        instructionCollector.get(event.getInstruction()).instructionInvoked();
    }

    public Report getReport() {
        Report report = new Report();
        for (ModuleCollector moduleCollector : modulesCollector.values()) {
            ModuleReport moduleReport = new ModuleReport(moduleCollector.getModule());
            report.addModuleReport(moduleReport);
            for (LineCollector lineCollector : moduleCollector.getLineCollectors()) {
                LineReport lineReport = new LineReport(lineCollector.getLineNumber());
                moduleReport.getLineReports().add(lineReport);
                for (InstructionCollector instructionCollector : lineCollector.getInstructionCollectors()) {
                    lineReport.getInstructionCoverageReports().add(new InstructionReport(instructionCollector.getInstruction(), instructionCollector.isInstructionInvoked()));
                }
            }
        }
        return report;
    }
}
