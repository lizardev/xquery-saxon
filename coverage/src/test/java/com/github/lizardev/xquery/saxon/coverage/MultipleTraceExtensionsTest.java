package com.github.lizardev.xquery.saxon.coverage;

import com.github.lizardev.xquery.saxon.coverage.report.ModuleReport;
import org.junit.Test;

import static com.github.lizardev.xquery.saxon.coverage.TestConstants.TOUR_MODULE;
import static org.assertj.core.api.Assertions.assertThat;

public class MultipleTraceExtensionsTest extends AbstractMultipleTraceExtensionsCoverageTest {

    @Test
    public void shouldCollectCoverageWhenMultipleTraceExtensionsAreProvided() {
        xQueryExecutorWithSingleTraceExtension.execute(TOUR_MODULE);
        xQueryExecutorWithMultipleTraceExtensions.execute(TOUR_MODULE);

        ModuleReport moduleReportForOneTraceExtension = coverageServiceForSingleTraceExtensionCases.getReport().getModuleReport(TOUR_MODULE);
        ModuleReport moduleReportForTwoTraceExtension = coverageServiceForMultipleTraceExtensionsCases.getReport().getModuleReport(TOUR_MODULE);
        assertThat(moduleReportForOneTraceExtension.getLineCoverage())
                .isEqualTo(moduleReportForTwoTraceExtension.getLineCoverage());
        assertThat(traceExtensionStubForMultipleTraceExtensionsCases.getTraceCodeInjector().numberOfInjectedInstructions()).isEqualTo(260);
        assertThat(traceExtensionStubForMultipleTraceExtensionsCases.getTraceListener().numberOfEnteredInstructions()).isEqualTo(117827);
    }
}