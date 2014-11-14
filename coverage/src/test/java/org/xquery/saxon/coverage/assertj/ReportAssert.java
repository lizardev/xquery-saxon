package org.xquery.saxon.coverage.assertj;

import com.google.common.base.Function;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Iterables;
import org.xquery.saxon.coverage.ModuleUri;
import org.xquery.saxon.coverage.report.ModuleReport;
import org.xquery.saxon.coverage.report.Report;

import static com.google.common.collect.Iterables.transform;

public class ReportAssert extends AbstractAssert<ReportAssert, Report> {

    private final Iterables iterables = Iterables.instance();

    protected ReportAssert(Report actual) {
        super(actual, ReportAssert.class);
    }

    public ReportAssert hasModuleReports(ModuleUri... moduleUris) {
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
        iterables.assertEmpty(info, actual.getModuleReports());
        return this;
    }

    public ReportAssert hasNumberOfModules(int number) {
        iterables.assertHasSize(info, actual.getModuleReports(), number);
        return this;
    }
}
