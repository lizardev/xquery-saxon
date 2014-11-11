package org.xquery.saxon.coverage;

import org.junit.Before;
import org.junit.Test;
import org.xquery.saxon.adapter.trace.SpiTraceExtensionProvider;
import org.xquery.saxon.adapter.trace.TraceExtension;
import org.xquery.saxon.coverage.report.ModuleReport;
import org.xquery.saxon.coverage.util.XQueryExecutor;

import static com.google.common.collect.Iterables.getFirst;
import static org.apache.commons.lang3.reflect.FieldUtils.writeStaticField;
import static org.xquery.saxon.coverage.assertj.ProjectAssertions.assertThat;

public class CoverageTest {

    private CoverageService coverageService;
    private XQueryExecutor xQueryExecutor;

    @Before
    public void initializeExecutorWithCoverage() {
        coverageService = new CoverageService();
        xQueryExecutor = new XQueryExecutor(new CoverageTraceExtension(
                coverageService.getCoverageInstructionInjector(),
                coverageService.getCoverageInstructionListener()));
    }

    @Test
    public void testCoverageOfTourModule() {
        xQueryExecutor.execute("/tour.xq");

        assertThat(coverageService.getReport().getModuleReports()).hasSize(1);
        ModuleReport moduleReport = getFirst(coverageService.getReport().getModuleReports(), null);
        assertThat(moduleReport)
                .hasCoverageCloseTo(0.91)
                .hasNotFullyCoveredLines(310, 312, 51, 55, 68, 73, 322, 152, 195);
    }

    @Test
    public void testCoverageOfTestModule() {
        xQueryExecutor.execute("/test.xq");

        assertThat(coverageService.getReport().getModuleReports()).hasSize(1);
        ModuleReport moduleReport = getFirst(coverageService.getReport().getModuleReports(), null);
        assertThat(moduleReport)
                .hasCoverageCloseTo(0.80)
                .hasNotFullyCoveredLines(6);
    }

    @Test
    public void functionsModuleShouldBeFullyCovered() {
        xQueryExecutor.execute("/functions.xq");

        assertThat(coverageService.getReport().getModuleReports()).hasSize(1);
        ModuleReport moduleReport = getFirst(coverageService.getReport().getModuleReports(), null);
        assertThat(moduleReport).isFullyCovered();
    }

    @Test
    public void shouldCreateSpiCoverageTraceExtensionProvider() throws IllegalAccessException {
        writeStaticField(CoverageService.class, "instance", null, true);
        TraceExtension traceExtension = new SpiTraceExtensionProvider().getTraceExtension();
        XQueryExecutor xQueryExecutor = new XQueryExecutor(traceExtension);

        xQueryExecutor.execute("/test.xq");

        ModuleReport moduleReport = getFirst(CoverageService.getInstance().getReport().getModuleReports(), null);
        assertThat(moduleReport)
                .hasCoverageCloseTo(0.80)
                .hasNotFullyCoveredLines(6);
    }
}