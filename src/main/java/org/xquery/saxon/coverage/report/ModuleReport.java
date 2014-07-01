package org.xquery.saxon.coverage.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ModuleReport {

    private final String module;
    private List<LineReport> lineReports = new ArrayList<LineReport>();
    private double lineCoverage;

    public ModuleReport(String module) {
        this.module = module;
    }

    public String getModule() {
        return module;
    }

    public List<LineReport> getLineReports() {
        return lineReports;
    }

    public Collection<LineReport> getNotFullyCoveredLines() {
        ArrayList<LineReport> lines = new ArrayList<LineReport>();
        for (LineReport lineReport : lineReports) {
            if (!lineReport.isFullyCovered()) {
                lines.add(lineReport);
            }
        }
        return lines;
    }

    public LineReport getLineReport(int lineNumber) {
        for (LineReport lineReport : lineReports) {
            if (lineReport.getLineNumber() == lineNumber) {
                return lineReport;
            }
        }
        return null;
    }

    public double getLineCoverage() {
        int coveredLines = 0;
        for (LineReport lineReport : lineReports) {
            if (lineReport.isFullyCovered()) {
                coveredLines++;
            }
        }
        return (double) coveredLines / lineReports.size();
    }
}
