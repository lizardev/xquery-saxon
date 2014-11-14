package org.xquery.saxon.coverage;

import org.junit.Test;
import org.xquery.saxon.adapter.trace.SpiTraceExtensionProvider;
import org.xquery.saxon.adapter.trace.TraceExtension;
import org.xquery.saxon.coverage.report.Report;
import org.xquery.saxon.coverage.util.XQueryExecutor;

import static org.apache.commons.lang3.reflect.FieldUtils.writeStaticField;
import static org.xquery.saxon.coverage.TestConstants.*;
import static org.xquery.saxon.coverage.assertj.ProjectAssertions.assertThat;
import static org.xquery.saxon.coverage.util.XQueryExecutorBuilder.xQueryExecutor;

public class BasicCoverageTest extends AbstractCoverageTest {

    @Test
    public void shouldCollectCoverageOfTourModule() {
        xqueryExecutor.execute(TOUR_MODULE);

        Report report = coverageService.getReport();
        assertThat(report).hasNumberOfModules(1);
        assertThat(report.getModuleReport(TOUR_MODULE))
                .hasCoverageCloseTo(0.91)
                .hasNotFullyCoveredLines(310, 312, 51, 55, 68, 73, 322, 152, 195);
    }

    @Test
    public void functionsModuleShouldBeFullyCovered() {
        xqueryExecutor.execute(FUNCTIONS_MODULE);

        Report report = coverageService.getReport();
        assertThat(report).hasNumberOfModules(1);
        assertThat(report.getModuleReport(FUNCTIONS_MODULE)).isFullyCovered();
    }

    @Test
    public void shouldCreateSpiCoverageTraceExtensionProvider() throws IllegalAccessException {
        writeStaticField(CoverageService.class, "instance", null, true);
        TraceExtension traceExtension = new SpiTraceExtensionProvider().getTraceExtension();
        XQueryExecutor xqueryExecutor = xQueryExecutor().withTraceExtension(traceExtension).build();

        xqueryExecutor.execute(ONE_LINE_MODULE);

        assertThat(CoverageService.getInstance().getReport().getModuleReport(ONE_LINE_MODULE)).isFullyCovered();
    }
}