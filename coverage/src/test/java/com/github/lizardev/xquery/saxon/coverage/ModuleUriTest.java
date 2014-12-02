package com.github.lizardev.xquery.saxon.coverage;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import com.github.lizardev.xquery.saxon.coverage.report.Report;

import java.io.IOException;

import static com.github.lizardev.xquery.saxon.coverage.TestConstants.ONE_LINE_MODULE;
import static com.github.lizardev.xquery.saxon.coverage.assertj.ProjectAssertions.assertThat;

public class ModuleUriTest extends AbstractCoverageTest {

    @Test
    public void shouldCollectCoverageWhenModuleHasUri() {
        xqueryExecutor.execute(ONE_LINE_MODULE);

        Report report = coverageService.getReport();
        assertThat(report).hasModuleReports(ONE_LINE_MODULE);
    }

    @Test
    public void shouldNotCollectCoverageWhenModuleDoesNotHaveUri() throws IOException {
        xqueryExecutor.execute(IOUtils.toString(ONE_LINE_MODULE.getUri()));

        Report report = coverageService.getReport();
        assertThat(report).isEmpty();
    }
}
