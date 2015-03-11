package com.github.lizardev.xquery.saxon.coverage.report;

import com.github.lizardev.xquery.saxon.coverage.ModuleUri;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

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
        ArrayList<LineReport> lines = new ArrayList<>(lineReports.size());
        for (LineReport lineReport : lineReports) {
            if (!lineReport.isFullyCovered()) {
                lines.add(lineReport);
            }
        }
        return lines;
    }

    public List<LineReport> getFullyCoveredLines() {
        ArrayList<LineReport> lines = new ArrayList<>(lineReports.size());
        for (LineReport lineReport : lineReports) {
            if (lineReport.isFullyCovered()) {
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

    public ModuleReport merge(ModuleReport moduleReport) {
        checkArgument(moduleUri.equals(moduleReport.moduleUri), "module uris must be the same");
        checkArgument(lineReports.size() == moduleReport.lineReports.size(), "numbers of lines must be the same");
        List<LineReport> mergedLineReports = new ArrayList<>();
        for (int i = 0; i < lineReports.size(); i++) {
            mergedLineReports.add(lineReports.get(i).merge(moduleReport.lineReports.get(i)));
        }
        return new ModuleReport(moduleUri, mergedLineReports);
    }
}
