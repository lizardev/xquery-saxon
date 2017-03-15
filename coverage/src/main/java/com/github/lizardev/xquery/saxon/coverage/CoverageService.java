package com.github.lizardev.xquery.saxon.coverage;

import com.github.lizardev.xquery.saxon.coverage.collect.DefaultCoverageInstructionEventHandler;
import com.github.lizardev.xquery.saxon.coverage.report.FileReportPrinter;
import com.github.lizardev.xquery.saxon.coverage.report.Report;
import com.github.lizardev.xquery.saxon.coverage.report.StreamReportPrinter;
import com.github.lizardev.xquery.saxon.coverage.trace.CoverageInstructionInjector;
import com.github.lizardev.xquery.saxon.coverage.trace.CoverageInstructionListener;

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
                @Override
                public void run() {
                    generateReport();
                }
            });
        }
    }

    public void generateReport() {
        Report report = defaultCoverageInstructionEventHandler.getReport();
        if (new SystemProperties().isCoverageReportPrintingOnConsoleEnabled()) {
            new StreamReportPrinter(System.err).print(report);
        }
        new FileReportPrinter().print(report);
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
