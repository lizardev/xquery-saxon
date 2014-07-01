package org.xquery.saxon.coverage;

import org.xquery.saxon.coverage.collect.DefaultCoverageExpressionEventHandler;
import org.xquery.saxon.coverage.report.Report;
import org.xquery.saxon.coverage.trace.CoverageExpressionInjector;
import org.xquery.saxon.coverage.trace.CoverageExpressionListener;

public class CoverageService {

    private final DefaultCoverageExpressionEventHandler defaultCoverageExpressionEventHandler;
    private final CoverageExpressionInjector coverageExpressionInjector;
    private final CoverageExpressionListener coverageExpressionListener;

    public CoverageService() {
        defaultCoverageExpressionEventHandler = new DefaultCoverageExpressionEventHandler();
        coverageExpressionInjector = new CoverageExpressionInjector(defaultCoverageExpressionEventHandler);
        coverageExpressionListener = new CoverageExpressionListener(defaultCoverageExpressionEventHandler);
    }

    public CoverageExpressionInjector getCoverageExpressionInjector() {
        return coverageExpressionInjector;
    }

    public CoverageExpressionListener getCoverageExpressionListener() {
        return coverageExpressionListener;
    }

    public Report getReport() {
        return defaultCoverageExpressionEventHandler.getReport();
    }
}
