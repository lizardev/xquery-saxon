package org.xquery.saxon.coverage.collect;

import net.sf.saxon.query.QueryModule;
import org.xquery.saxon.coverage.ModuleUri;
import org.xquery.saxon.coverage.report.InstructionReport;
import org.xquery.saxon.coverage.report.LineReport;
import org.xquery.saxon.coverage.report.ModuleReport;
import org.xquery.saxon.coverage.report.Report;
import org.xquery.saxon.coverage.trace.CoverageInstructionCreatedEvent;
import org.xquery.saxon.coverage.trace.CoverageInstructionEventHandler;
import org.xquery.saxon.coverage.trace.CoverageInstructionInvokedEvent;
import org.xquery.saxon.coverage.trace.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultCoverageInstructionEventHandler implements CoverageInstructionEventHandler {

    private Map<ModuleUri, ModuleCollector> modulesCollector = new HashMap<>();
    private Map<Identifier, InstructionCollector> instructionCollectors = new HashMap<>();

    public void handle(CoverageInstructionCreatedEvent event) {
        InstructionCollector instructionCollector = getModuleCollector(getModuleUri(event.getQueryModule()))
                .instructionCreated(event.getInstruction());
        instructionCollectors.put(event.getInstruction().getIdentifier(), instructionCollector);
    }

    private ModuleUri getModuleUri(QueryModule queryModule) {
        if (queryModule.getLocationURI() == null) {
            return null;
        } else {
            return ModuleUri.fromUri(queryModule.getLocationURI());
        }
    }

    public void handle(CoverageInstructionInvokedEvent event) {
        instructionCollectors.get(event.getIdentifier()).instructionInvoked();
    }

    private ModuleCollector getModuleCollector(ModuleUri module) {
        ModuleCollector moduleCollector = modulesCollector.get(module);
        if (moduleCollector == null) {
            moduleCollector = new ModuleCollector(module);
            modulesCollector.put(module, moduleCollector);
        }
        return moduleCollector;
    }

    public Report getReport() {
        Report report = new Report();
        for (Map.Entry<ModuleUri, ModuleCollector> entry : modulesCollector.entrySet()) {
            if (entry.getKey() != null) {
                report.addModuleReport(getModuleReport(entry.getValue()));
            }
        }
        return report;
    }

    private ModuleReport getModuleReport(ModuleCollector moduleCollector) {
        List<LineReport> lineReports = new ArrayList<>();
        for (LineCollector lineCollector : moduleCollector.getLineCollectors()) {
            lineReports.add(getLineReport(lineCollector));
        }
        return new ModuleReport(moduleCollector.getModuleUri(), lineReports);
    }

    private LineReport getLineReport(LineCollector lineCollector) {
        List<InstructionReport> instructionReports = new ArrayList<InstructionReport>(lineCollector.getInstructionCollectors().size());
        for (InstructionCollector instructionCollector : lineCollector.getInstructionCollectors()) {
            instructionReports.add(new InstructionReport(instructionCollector.getInstruction(), instructionCollector.isInstructionInvoked()));
        }
        return new LineReport(lineCollector.getLineNumber(), instructionReports);
    }
}
