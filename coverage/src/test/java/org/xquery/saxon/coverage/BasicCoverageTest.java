package org.xquery.saxon.coverage;

import org.junit.Test;
import org.xquery.saxon.coverage.report.Report;

import static org.xquery.saxon.coverage.TestConstants.FUNCTIONS_MODULE;
import static org.xquery.saxon.coverage.TestConstants.TOUR_MODULE;
import static org.xquery.saxon.coverage.assertj.ProjectAssertions.assertThat;

public class BasicCoverageTest extends AbstractCoverageTest {

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
}