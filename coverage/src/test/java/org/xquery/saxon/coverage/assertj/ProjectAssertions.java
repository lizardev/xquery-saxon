package org.xquery.saxon.coverage.assertj;

import org.assertj.core.api.Assertions;
import org.xquery.saxon.coverage.report.ModuleReport;

public class ProjectAssertions extends Assertions {

    private ProjectAssertions() {
    }

    public static ModuleReportAssert assertThat(ModuleReport actual) {
        return new ModuleReportAssert(actual);
    }
}
