package org.xquery.saxon.coverage.report;

import java.util.ArrayList;
import java.util.List;

public class LineReport {

    private int lineNumber;
    private List<InstructionReport> instructionCoverageReports = new ArrayList<InstructionReport>();

    public LineReport(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public List<InstructionReport> getInstructionCoverageReports() {
        return instructionCoverageReports;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public boolean isFullyCovered() {
        for (InstructionReport instructionCoverageReport : instructionCoverageReports) {
            if (!instructionCoverageReport.isCovered()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "LineReport{" +
                "lineNumber=" + lineNumber +
                ", instructionCoverageReports=" + instructionCoverageReports +
                '}';
    }
}
