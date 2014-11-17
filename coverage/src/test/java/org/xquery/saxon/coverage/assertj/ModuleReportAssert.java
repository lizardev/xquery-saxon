package org.xquery.saxon.coverage.assertj;

import com.google.common.base.Function;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.data.Offset;
import org.assertj.core.internal.Booleans;
import org.assertj.core.internal.Doubles;
import org.assertj.core.internal.Iterables;
import org.xquery.saxon.coverage.report.LineReport;
import org.xquery.saxon.coverage.report.ModuleReport;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.transform;

public class ModuleReportAssert extends AbstractAssert<ModuleReportAssert, ModuleReport> {

    private static final Offset<Double> DEFAULT_COVERAGE_OFFSET = Offset.offset(0.01);

    protected ModuleReportAssert(ModuleReport actual) {
        super(actual, ModuleReportAssert.class);
    }

    public ModuleReportAssert hasCoverageCloseTo(double coverage) {
        info.description("has coverage close to (default offset is %s)", DEFAULT_COVERAGE_OFFSET.value);
        Doubles.instance().assertIsCloseTo(info, actual.getLineCoverage(), coverage, DEFAULT_COVERAGE_OFFSET);
        return this;
    }

    public ModuleReportAssert hasNotFullyCoveredLines(int... lineNumbers) {
        info.description("has not fully covered lines");
        List<Integer> notFullyCoveredLineNumbers = transform(actual.getNotFullyCoveredLines(),
                new Function<LineReport, Integer>() {
                    public Integer apply(LineReport input) {
                        return input.getLineNumber();
                    }
                });
        List<Integer> lineNumbersAsList = new ArrayList<>();
        for (int lineNumber : lineNumbers) {
            lineNumbersAsList.add(lineNumber);
        }
        Iterables.instance().assertContainsAll(info, notFullyCoveredLineNumbers, lineNumbersAsList);
        return this;
    }

    public ModuleReportAssert isFullyCovered() {
        info.description("is fully covered");
        Booleans.instance().assertEqual(info, actual.isFullyCovered(), true);
        return this;
    }
}
