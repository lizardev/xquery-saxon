package com.github.lizardev.xquery.saxon.coverage;

import com.github.lizardev.xquery.saxon.coverage.report.LineReport;
import com.github.lizardev.xquery.saxon.coverage.report.ModuleReport;
import org.junit.Test;

import static com.github.lizardev.xquery.saxon.coverage.TestConstants.DUPLICATED_TRACE_EXPRESSION_MODULE;
import static com.github.lizardev.xquery.saxon.coverage.assertj.ProjectAssertions.assertThat;

public class SimplifyTraceExpressionTest extends AbstractCoverageTest {

    @Test
    public void shouldSimplifyDuplicatedTraceExpression() throws Exception {
        xqueryExecutor.execute(DUPLICATED_TRACE_EXPRESSION_MODULE);

        ModuleReport moduleReport = coverageService.getReport().getModuleReport(DUPLICATED_TRACE_EXPRESSION_MODULE);
        assertThat(moduleReport).hasFullyCoveredLines(1, 2);
        LineReport line2Report = moduleReport.getLineReport(2);
        assertThat(line2Report.getInstructionReports()).hasSize(1);
        assertThat(line2Report.getInstructionReports().get(0).getInstruction()).isEqualTo("true()");
        LineReport line4Report = moduleReport.getLineReport(4);
        assertThat(line4Report.getInstructionReports()).hasSize(1);
        assertThat(line4Report.getInstructionReports().get(0).getInstruction()).isEqualTo("false()");
    }
}
