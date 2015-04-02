package com.github.lizardev.xquery.saxon.coverage;

import com.github.lizardev.xquery.saxon.coverage.report.Report;
import org.junit.Test;

import static com.github.lizardev.xquery.saxon.coverage.TestConstants.FUNCTIONS_MODULE;
import static com.github.lizardev.xquery.saxon.coverage.TestConstants.FUNCTX_MAIN_MODULE;
import static com.github.lizardev.xquery.saxon.coverage.TestConstants.FUNCTX_MODULE;
import static com.github.lizardev.xquery.saxon.coverage.TestConstants.TOUR_MODULE;
import static com.github.lizardev.xquery.saxon.coverage.assertj.ProjectAssertions.assertThat;

public class SupportedLanguageStructureTest extends AbstractCoverageTest {

    @Test
    public void shouldCollectCoverageOfTourModule() {
        xqueryExecutor.execute(TOUR_MODULE);

        Report report = coverageService.getReport();
        assertThat(report).hasNumberOfModules(1);
        assertThat(report.getModuleReport(TOUR_MODULE))
                .hasCoverageCloseTo(0.91)
                .hasNotFullyCoveredLines(51, 55, 68, 73, 152, 195, 310, 312, 322);
    }

    @Test
    public void functionsModuleShouldBeFullyCovered() {
        xqueryExecutor.execute(FUNCTIONS_MODULE);

        Report report = coverageService.getReport();
        assertThat(report).hasNumberOfModules(1);
        assertThat(report.getModuleReport(FUNCTIONS_MODULE)).isFullyCovered();
    }

    @Test
    public void shouldCollectCoverageOfFunctxModules() {
        boolean result = xqueryExecutor.execute(FUNCTX_MAIN_MODULE);

        assertThat(result).isTrue();
        Report report = coverageService.getReport();
        assertThat(report.getModuleReport(FUNCTX_MAIN_MODULE)).isFullyCovered();
        assertThat(report.getModuleReport(FUNCTX_MODULE)).hasFullyCoveredLines(1341);
    }
}