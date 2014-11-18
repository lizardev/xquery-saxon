package org.xquery.saxon.coverage.report;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class LineReport {

    private final int lineNumber;
    private final List<InstructionReport> instructionReports;

    public LineReport(int lineNumber, List<InstructionReport> instructionReports) {
        this.lineNumber = lineNumber;
        this.instructionReports = ImmutableList.copyOf(instructionReports);
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE)
                .append("lineNumber", lineNumber)
                .append("instructionCoverageReports" + instructionReports)
                .build();
    }

    public LineReport merge(LineReport lineReport) {
        checkArgument(lineNumber == lineReport.lineNumber, "line numbers must be the same");
        checkArgument(instructionReports.size() == lineReport.instructionReports.size(), "numbers of instructions must be the same");
        List<InstructionReport> mergedInstructionReports = new ArrayList<>();
        for (int i = 0; i < instructionReports.size(); i++) {
            mergedInstructionReports.add(instructionReports.get(i).merge(lineReport.instructionReports.get(i)));
        }
        return new LineReport(lineNumber, mergedInstructionReports);
    }
}
