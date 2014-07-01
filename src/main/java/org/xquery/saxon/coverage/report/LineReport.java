package org.xquery.saxon.coverage.report;

import java.util.ArrayList;
import java.util.List;

public class LineReport {

    private int lineNumber;
    private List<ExpressionReport> expressionCoverageReports = new ArrayList<ExpressionReport>();

    public LineReport(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public List<ExpressionReport> getExpressionCoverageReports() {
        return expressionCoverageReports;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public boolean isFullyCovered() {
        for (ExpressionReport expressionCoverageReport : expressionCoverageReports) {
            if (!expressionCoverageReport.isCovered()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "LineReport{" +
                "lineNumber=" + lineNumber +
                ", expressionCoverageReports=" + expressionCoverageReports +
                '}';
    }
}
