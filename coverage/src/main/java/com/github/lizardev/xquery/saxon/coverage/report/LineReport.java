package com.github.lizardev.xquery.saxon.coverage.report;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class LineReport {

    private final int lineNumber;
    private final List<InstructionReport> instructionReports;
    private final String sourceCodeLine;

    public LineReport(int lineNumber, List<InstructionReport> instructionReports) {
        this(lineNumber, instructionReports, null);
    }

    public LineReport(int lineNumber, String sourceCodeLine) {
        this(lineNumber, ImmutableList.<InstructionReport>of(), sourceCodeLine);
    }

    public LineReport(int lineNumber, List<InstructionReport> instructionReports, String sourceCodeLine) {
        this.lineNumber = lineNumber;
        this.instructionReports = ImmutableList.copyOf(instructionReports);
        this.sourceCodeLine = sourceCodeLine;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public boolean isFullyCovered() {
        for (InstructionReport instructionReport : instructionReports) {
            if (!instructionReport.isCovered()) {
                return false;
            }
        }
        return true;
    }

    public boolean isCoverageCollected() {
        return !instructionReports.isEmpty();
    }

    public List<InstructionReport> getInstructionReports() {
        return instructionReports;
    }

    public String getSourceCodeLine() {
        return sourceCodeLine;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE)
                .append("lineNumber", lineNumber)
                .append("instructionCoverageReports", instructionReports)
                .append("sourceCodeLine", sourceCodeLine)
                .build();
    }

    public LineReport merge(LineReport lineReport) {
        checkArgument(lineNumber == lineReport.lineNumber, "line numbers must be the same");
        checkArgument(instructionReports.size() == lineReport.instructionReports.size(), "numbers of instructions must be the same");
        checkArgument(Objects.equals(sourceCodeLine, lineReport.sourceCodeLine), "source code lines must be the same");
        List<InstructionReport> mergedInstructionReports = new ArrayList<>();
        for (int i = 0; i < instructionReports.size(); i++) {
            mergedInstructionReports.add(instructionReports.get(i).merge(lineReport.instructionReports.get(i)));
        }
        return new LineReport(lineNumber, mergedInstructionReports, sourceCodeLine);
    }
}
