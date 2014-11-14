package org.xquery.saxon.coverage.report;

import com.google.common.collect.ImmutableList;
import org.xquery.saxon.coverage.ModuleUri;

import java.util.ArrayList;
import java.util.List;

public class ModuleReport {

    private final ModuleUri moduleUri;
    private final List<LineReport> lineReports;

    public ModuleReport(ModuleUri moduleUri, List<LineReport> lineReports) {
        this.moduleUri = moduleUri;
        this.lineReports = ImmutableList.copyOf(lineReports);
    }

    public ModuleUri getModuleUri() {
        return moduleUri;
    }

    public List<LineReport> getLineReports() {
        return lineReports;
    }

    public List<LineReport> getNotFullyCoveredLines() {
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

    public boolean isFullyCovered() {
        for (LineReport lineReport : lineReports) {
            if (!lineReport.isFullyCovered()) {
                return false;
            }
        }
        return true;
    }
}
