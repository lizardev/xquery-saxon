package org.xquery.saxon.coverage;

import com.google.common.collect.Iterables;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.xquery.saxon.coverage.report.LineReport;
import org.xquery.saxon.coverage.report.ModuleReport;
import org.xquery.saxon.coverage.util.XQueryExecutor;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class CoverageTest {

    private CoverageService coverageService;
    private XQueryExecutor xQueryExecutor;

    @Before
    public void initializeExecutorWithCoverage() {
        coverageService = new CoverageService();
        xQueryExecutor = new XQueryExecutor(coverageService.getTraceProvider());
    }

    @Test
    public void testCoverageOfTourModule() {
        xQueryExecutor.execute("/tour.xq");

        assertThat(coverageService.getReport().getModuleReports(), hasSize(1));
        ModuleReport moduleReport = Iterables.getFirst(coverageService.getReport().getModuleReports(), null);
        assertThat(moduleReport.getLineCoverage(), is(closeTo(0.91, 0.01)));
        assertThat(moduleReport.getNotFullyCoveredLines(), containsInAnyOrder(
                line(310), line(312), line(51), line(55), line(68), line(73), line(322), line(152), line(195)));
    }

    @Test
    public void testCoverageOfTestModule() {
        xQueryExecutor.execute("/test.xq");

        assertThat(coverageService.getReport().getModuleReports(), hasSize(1));
        ModuleReport moduleReport = Iterables.getFirst(coverageService.getReport().getModuleReports(), null);
        assertThat(moduleReport.getNotFullyCoveredLines(), contains(line(6)));
        assertThat(moduleReport.getLineCoverage(), is(closeTo(0.80, 0.01)));

    }

    @Test
    public void functionsModuleShouldBeFullyCovered() {
        xQueryExecutor.execute("/functions.xq");

        assertThat(coverageService.getReport().getModuleReports(), hasSize(1));
        ModuleReport moduleReport = Iterables.getFirst(coverageService.getReport().getModuleReports(), null);
        assertThat(moduleReport.getNotFullyCoveredLines(), empty());
        assertThat(moduleReport.getLineCoverage(), is(closeTo(1.0, 0.01)));

    }

    private static Matcher<LineReport> line(final int lineNumber) {
        return new CustomTypeSafeMatcher<LineReport>("line report with line number = " + lineNumber) {

            @Override
            protected boolean matchesSafely(LineReport lineReport) {
                return lineReport.getLineNumber() == lineNumber;
            }
        };
    }
}