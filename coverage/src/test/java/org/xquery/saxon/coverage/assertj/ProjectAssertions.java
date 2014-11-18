package org.xquery.saxon.coverage.assertj;

import org.assertj.core.api.Assertions;
import org.xquery.saxon.coverage.report.ModuleReport;
import org.xquery.saxon.coverage.report.Report;

public final class ProjectAssertions extends Assertions {

    private ProjectAssertions() {
    }

    public static ReportAssert assertThat(Report actual) {
        return new ReportAssert(actual);
    }

    public static ModuleReportAssert assertThat(ModuleReport actual) {
        return new ModuleReportAssert(actual);
    }
}
