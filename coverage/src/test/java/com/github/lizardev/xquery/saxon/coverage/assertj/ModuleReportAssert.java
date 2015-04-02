package com.github.lizardev.xquery.saxon.coverage.assertj;

import com.github.lizardev.xquery.saxon.coverage.report.LineReport;
import com.github.lizardev.xquery.saxon.coverage.report.ModuleReport;
import com.google.common.base.Function;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.data.Offset;
import org.assertj.core.internal.Booleans;
import org.assertj.core.internal.Doubles;
import org.assertj.core.internal.Iterables;

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
        assertLineNumbers(actual.getNotFullyCoveredLines(), lineNumbers);
        return this;
    }

    public ModuleReportAssert hasFullyCoveredLines(int... lineNumbers) {
        info.description("has fully covered lines");
        assertLineNumbers(actual.getFullyCoveredLines(), lineNumbers);
        return this;
    }

    private void assertLineNumbers(List<LineReport> actualLineReports, int[] expectedLineNumbers) {
        List<Integer> actualLineNumbers = transform(actualLineReports,
                new Function<LineReport, Integer>() {
                    public Integer apply(LineReport input) {
                        return input.getLineNumber();
                    }
                });
        List<Integer> expectedLineNumbersAsList = new ArrayList<>();
        for (int expectedLineNumber : expectedLineNumbers) {
            expectedLineNumbersAsList.add(expectedLineNumber);
        }
        Iterables.instance().assertContainsAll(info, actualLineNumbers, expectedLineNumbersAsList);
    }

    public ModuleReportAssert isFullyCovered() {
        info.description("is fully covered");
        Booleans.instance().assertEqual(info, actual.isFullyCovered(), true);
        return this;
    }
}
