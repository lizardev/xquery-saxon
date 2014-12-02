package com.github.lizardev.xquery.saxon.coverage.assertj;

import com.google.common.base.Function;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Iterables;
import com.github.lizardev.xquery.saxon.coverage.ModuleUri;
import com.github.lizardev.xquery.saxon.coverage.report.ModuleReport;
import com.github.lizardev.xquery.saxon.coverage.report.Report;

import static com.google.common.collect.Iterables.transform;

public class ReportAssert extends AbstractAssert<ReportAssert, Report> {

    private final Iterables iterables = Iterables.instance();

    protected ReportAssert(Report actual) {
        super(actual, ReportAssert.class);
    }

    public ReportAssert hasModuleReports(ModuleUri... moduleUris) {
        info.description("has module reports");
        iterables.assertHasSize(info, actual.getModuleReports(), moduleUris.length);
        Iterable<ModuleUri> actualModuleUris = transform(actual.getModuleReports(), new Function<ModuleReport, ModuleUri>() {
            @Override
            public ModuleUri apply(ModuleReport input) {
                return input.getModuleUri();
            }
        });
        iterables.assertContainsAll(info, actualModuleUris, actualModuleUris);
        return this;
    }

    public ReportAssert isEmpty() {
        info.description("is empty");
        iterables.assertEmpty(info, actual.getModuleReports());
        return this;
    }

    public ReportAssert hasNumberOfModules(int number) {
        info.description("has number of modules");
        iterables.assertHasSize(info, actual.getModuleReports(), number);
        return this;
    }
}
