package com.github.lizardev.xquery.saxon.coverage;

import com.github.lizardev.xquery.saxon.coverage.report.LineReport;
import com.github.lizardev.xquery.saxon.coverage.report.ModuleReport;
import com.github.lizardev.xquery.saxon.coverage.util.XQueryExecutor;
import org.junit.Test;

import static com.github.lizardev.xquery.saxon.coverage.TestConstants.DUPLICATED_TRACE_EXPRESSION_MODULE;
import static com.github.lizardev.xquery.saxon.coverage.assertj.ProjectAssertions.assertThat;

public class SimplifyTraceExpressionTest extends AbstractMultipleTraceExtensionsCoverageTest {

    @Test
    public void shouldSimplifyDuplicatedTraceExpressionWhenSingleTraceExtensionIsProvided() {
        shouldSimplifyDuplicatedTraceExpression(xQueryExecutorWithSingleTraceExtension, coverageServiceForSingleTraceExtensionCases);
    }

    @Test
    public void shouldSimplifyDuplicatedTraceExpressionWhenMultipleTraceExtensionsAreProvided() {
        shouldSimplifyDuplicatedTraceExpression(xQueryExecutorWithMultipleTraceExtensions, coverageServiceForMultipleTraceExtensionsCases);
        assertThat(traceExtensionStubForMultipleTraceExtensionsCases.getTraceCodeInjector().numberOfInjectedInstructions()).isEqualTo(6);
        assertThat(traceExtensionStubForMultipleTraceExtensionsCases.getTraceListener().numberOfEnteredInstructions()).isEqualTo(5);
    }

    private void shouldSimplifyDuplicatedTraceExpression(XQueryExecutor xQueryExecutor, CoverageService coverageService) {
        xQueryExecutor.execute(DUPLICATED_TRACE_EXPRESSION_MODULE);

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