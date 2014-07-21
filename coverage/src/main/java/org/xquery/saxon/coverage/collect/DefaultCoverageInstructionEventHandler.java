package org.xquery.saxon.coverage.collect;

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

	private Map<String, ModuleCollector> modulesCollector = new HashMap<String, ModuleCollector>();
	private Map<Identifier, InstructionCollector> instructionCollectors = new HashMap<Identifier, InstructionCollector>();

	public void handle(CoverageInstructionCreatedEvent event) {
		InstructionCollector instructionCollector = getModuleCollector(event.getQueryModule().getSystemId())
				.instructionCreated(event.getInstruction());
		instructionCollectors.put(event.getInstruction().getIdentifier(), instructionCollector);
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
		instructionCollectors.get(event.getIdentifier()).instructionInvoked();
	}

	public Report getReport() {
		Report report = new Report();
		for (ModuleCollector moduleCollector : modulesCollector.values()) {
			report.addModuleReport(getModuleReport(moduleCollector));
		}
		return report;
	}

	private ModuleReport getModuleReport(ModuleCollector moduleCollector) {
		List<LineReport> lineReports = new ArrayList<LineReport>();
		for (LineCollector lineCollector : moduleCollector.getLineCollectors()) {
			lineReports.add(getLineReport(lineCollector));
		}
		return new ModuleReport(moduleCollector.getModule(), lineReports);
	}

	private LineReport getLineReport(LineCollector lineCollector) {
		List<InstructionReport> instructionReports = new ArrayList<InstructionReport>(lineCollector.getInstructionCollectors().size());
		for (InstructionCollector instructionCollector : lineCollector.getInstructionCollectors()) {
			instructionReports.add(new InstructionReport(instructionCollector.getInstruction(), instructionCollector.isInstructionInvoked()));
		}
		return new LineReport(lineCollector.getLineNumber(), instructionReports);
	}
}
