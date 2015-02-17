package com.github.lizardev.xquery.saxon.coverage;

import com.github.lizardev.xquery.saxon.coverage.report.ModuleReport;
import com.github.lizardev.xquery.saxon.support.trace.TraceExtension;
import com.github.lizardev.xquery.saxon.support.trace.TraceExtensionComposite;
import com.github.lizardev.xquery.saxon.support.trace.TraceListenerAdapter;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import net.sf.saxon.expr.Container;
import net.sf.saxon.expr.StaticContext;
import net.sf.saxon.expr.flwor.Clause;
import net.sf.saxon.lib.TraceListener;
import net.sf.saxon.trace.TraceCodeInjector;
import org.junit.Test;

import static com.github.lizardev.xquery.saxon.coverage.TestConstants.TOUR_MODULE;
import static com.github.lizardev.xquery.saxon.coverage.util.XQueryExecutorBuilder.xQueryExecutor;
import static org.assertj.core.api.Assertions.assertThat;

public class AdditionalTraceExtensionTest {
    private CoverageService coverageService1 = new CoverageService();
    private CoverageTraceExtension coverageTraceExtension1 = new CoverageTraceExtension(
            coverageService1.getCoverageInstructionInjector(),
            coverageService1.getCoverageInstructionListener());
    private CoverageService coverageService2 = new CoverageService();
    private CoverageTraceExtension coverageTraceExtension2 = new CoverageTraceExtension(
            coverageService2.getCoverageInstructionInjector(),
            coverageService2.getCoverageInstructionListener());
    private FakeTraceExtension fakeTraceExtension = new FakeTraceExtension();

    @Test
    public void shouldCollectCoverageWhenAdditionalTraceExtensionIsProvided() {
        xQueryExecutor().withTraceExtension(coverageTraceExtension1)
                .build().execute(TOUR_MODULE);
        xQueryExecutor().withTraceExtension(new TraceExtensionComposite(ImmutableList.of(coverageTraceExtension2, fakeTraceExtension)))
                .build().execute(TOUR_MODULE);

        ModuleReport moduleReportForOneTraceExtension = coverageService1.getReport().getModuleReport(TOUR_MODULE);
        ModuleReport moduleReportForTwoTraceExtension = coverageService2.getReport().getModuleReport(TOUR_MODULE);
        assertThat(moduleReportForOneTraceExtension.getLineCoverage())
                .isEqualTo(moduleReportForTwoTraceExtension.getLineCoverage());
    }

    private static class FakeTraceExtension implements TraceExtension {
        @Override
        public Optional<TraceCodeInjector> getTraceCodeInjector() {
            return Optional.<TraceCodeInjector>of(new FakeTraceCodeInjector());
        }

        @Override
        public Optional<TraceListener> getTraceListener() {
            return Optional.<TraceListener>of(new FakeTraceLister());
        }

        @Override
        public boolean allowsOptimization() {
            return false;
        }

        @Override
        public int getOrder() {
            return 0;
        }

    }

    private static class FakeTraceCodeInjector extends TraceCodeInjector {
        @Override
        public Clause injectClause(Clause target, StaticContext env, Container container) {
            Clause clause = super.injectClause(target, env, container);
            clause.setLocationId(target.getLocationId());
            return clause;
        }
    }

    private static class FakeTraceLister extends TraceListenerAdapter {
    }
}
