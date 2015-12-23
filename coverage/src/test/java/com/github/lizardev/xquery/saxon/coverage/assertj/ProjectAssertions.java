package com.github.lizardev.xquery.saxon.coverage.assertj;

import com.github.lizardev.xquery.saxon.coverage.report.ModuleReport;
import com.github.lizardev.xquery.saxon.coverage.report.Report;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ObjectAssert;

import java.nio.file.Path;

public final class ProjectAssertions extends Assertions {

    private ProjectAssertions() {
    }

    public static ReportAssert assertThat(Report actual) {
        return new ReportAssert(actual);
    }

    public static ModuleReportAssert assertThat(ModuleReport actual) {
        return new ModuleReportAssert(actual);
    }

    public static ObjectAssert<Path> assertThat(Path path) {
        return (ObjectAssert) Assertions.assertThat((Object) path);
    }
}
