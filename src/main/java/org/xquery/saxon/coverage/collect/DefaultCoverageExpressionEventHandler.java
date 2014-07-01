package org.xquery.saxon.coverage.collect;

import org.xquery.saxon.coverage.report.ExpressionReport;
import org.xquery.saxon.coverage.report.LineReport;
import org.xquery.saxon.coverage.report.ModuleReport;
import org.xquery.saxon.coverage.report.Report;
import org.xquery.saxon.coverage.trace.CoverageExpression;
import org.xquery.saxon.coverage.trace.CoverageExpressionCreatedEvent;
import org.xquery.saxon.coverage.trace.CoverageExpressionEventHandler;
import org.xquery.saxon.coverage.trace.CoverageExpressionInvokedEvent;

import java.util.HashMap;
import java.util.Map;

public class DefaultCoverageExpressionEventHandler implements CoverageExpressionEventHandler {

    private Map<String, ModuleCollector> modulesCollector = new HashMap<String, ModuleCollector>();
    private Map<CoverageExpression, ExpressionCollector> expressionsCollector = new HashMap<CoverageExpression, ExpressionCollector>();

    public void handle(CoverageExpressionCreatedEvent event) {
        ExpressionCollector expressionCollector = getModuleCollector(event.getQueryModule().getSystemId())
                .expressionCreated(event.getExpression());
        expressionsCollector.put(event.getExpression(), expressionCollector);
    }

    private ModuleCollector getModuleCollector(String module) {
        ModuleCollector moduleCollector = modulesCollector.get(module);
        if (moduleCollector == null) {
            moduleCollector = new ModuleCollector(module);
            modulesCollector.put(module, moduleCollector);
        }
        return moduleCollector;
    }

    public void handle(CoverageExpressionInvokedEvent event) {
        expressionsCollector.get(event.getExpression()).expressionInvoked();
    }

    public Report getReport() {
        Report report = new Report();
        for (ModuleCollector moduleCollector : modulesCollector.values()) {
            ModuleReport moduleReport = new ModuleReport(moduleCollector.getModule());
            report.addModuleReport(moduleReport);
            for (LineCollector lineCollector : moduleCollector.getLineCollectors()) {
                LineReport lineReport = new LineReport(lineCollector.getLineNumber());
                moduleReport.getLineReports().add(lineReport);
                for (ExpressionCollector expressionCollector : lineCollector.getExpressionCollectors()) {
                    lineReport.getExpressionCoverageReports().add(new ExpressionReport(expressionCollector.getExpression(), expressionCollector.isExpressionInvoked()));
                }
            }
        }
        return report;
    }
}
