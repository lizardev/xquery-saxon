package org.xquery.saxon.coverage.report;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

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
}
