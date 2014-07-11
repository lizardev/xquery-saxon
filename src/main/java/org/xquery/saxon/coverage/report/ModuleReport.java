package org.xquery.saxon.coverage.report;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ModuleReport {

    private final String module;
    private final List<LineReport> lineReports;

    public ModuleReport(String module, List<LineReport> lineReports) {
        this.module = module;
		this.lineReports = ImmutableList.copyOf(lineReports);
    }

    public String getModule() {
        return module;
    }

    public List<LineReport> getLineReports() {
        return lineReports;
    }

    public Collection<LineReport> getNotFullyCoveredLines() {
        ArrayList<LineReport> lines = new ArrayList<LineReport>(lineReports.size());
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
