package org.xquery.saxon.coverage;

import org.apache.commons.lang3.StringUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.RestoreSystemProperties;
import org.xquery.saxon.support.trace.SpiTraceExtensionProvider;
import org.xquery.saxon.support.trace.TraceExtension;
import org.xquery.saxon.coverage.util.XQueryExecutor;

import static org.apache.commons.lang3.reflect.FieldUtils.writeStaticField;
import static org.xquery.saxon.coverage.SystemProperties.COVERAGE_ENABLED;
import static org.xquery.saxon.coverage.TestConstants.ONE_LINE_MODULE;
import static org.xquery.saxon.coverage.assertj.ProjectAssertions.assertThat;
import static org.xquery.saxon.coverage.util.XQueryExecutorBuilder.xQueryExecutor;

public class SpiCoverageTraceExtensionProviderTest {

    @Rule public RestoreSystemProperties restoreSystemProperties = new RestoreSystemProperties(COVERAGE_ENABLED);

    @Test
    public void shouldCreateSpiCoverageTraceExtensionProvider() throws IllegalAccessException {
        writeStaticField(CoverageService.class, "instance", null, true);
        System.setProperty(COVERAGE_ENABLED, StringUtils.EMPTY);
        TraceExtension traceExtension = new SpiTraceExtensionProvider().getTraceExtension();
        XQueryExecutor xqueryExecutor = xQueryExecutor().withTraceExtension(traceExtension).build();

        xqueryExecutor.execute(ONE_LINE_MODULE);

        assertThat(CoverageService.getInstance().getReport().getModuleReport(ONE_LINE_MODULE)).isFullyCovered();
    }
}
