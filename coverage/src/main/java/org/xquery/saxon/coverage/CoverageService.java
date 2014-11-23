package org.xquery.saxon.coverage;

import org.xquery.saxon.coverage.collect.DefaultCoverageInstructionEventHandler;
import org.xquery.saxon.coverage.report.FileReportPrinter;
import org.xquery.saxon.coverage.report.Report;
import org.xquery.saxon.coverage.report.StreamReportPrinter;
import org.xquery.saxon.coverage.trace.CoverageInstructionInjector;
import org.xquery.saxon.coverage.trace.CoverageInstructionListener;

public class CoverageService {

    private static CoverageService instance;
    private final DefaultCoverageInstructionEventHandler defaultCoverageInstructionEventHandler;
    private final CoverageInstructionInjector coverageInstructionInjector;
    private final CoverageInstructionListener coverageInstructionListener;

    public CoverageService() {
        defaultCoverageInstructionEventHandler = new DefaultCoverageInstructionEventHandler();
        coverageInstructionInjector = new CoverageInstructionInjector(defaultCoverageInstructionEventHandler);
        coverageInstructionListener = new CoverageInstructionListener(defaultCoverageInstructionEventHandler);
        if (new SystemProperties().isCoverageReportPrintingOnShutdownEnabled()) {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    Report report = defaultCoverageInstructionEventHandler.getReport();
                    new StreamReportPrinter(System.err).print(report);
                    new FileReportPrinter().print(report);
                }
            });
        }
    }

    public Report getReport() {
        return defaultCoverageInstructionEventHandler.getReport();
    }

    public CoverageInstructionInjector getCoverageInstructionInjector() {
        return coverageInstructionInjector;
    }

    public CoverageInstructionListener getCoverageInstructionListener() {
        return coverageInstructionListener;
    }

    public static synchronized CoverageService getInstance() {
        if (instance == null) {
            instance = new CoverageService();
        }
        return instance;
    }
}
