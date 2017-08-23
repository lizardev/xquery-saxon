package com.github.lizardev.xquery.saxon.coverage;

import com.github.lizardev.xquery.saxon.coverage.collect.DefaultCoverageInstructionEventHandler;
import com.github.lizardev.xquery.saxon.coverage.report.FileReportPrinter;
import com.github.lizardev.xquery.saxon.coverage.report.Report;
import com.github.lizardev.xquery.saxon.coverage.report.ReportRepository;
import com.github.lizardev.xquery.saxon.coverage.trace.CoverageInstructionInjector;
import com.github.lizardev.xquery.saxon.coverage.trace.CoverageInstructionListener;

import java.io.File;

public class CoverageService {

    public static final String DEFAULT_REPORT_DIR = "build/reports/xquery-saxon-coverage";
    private final SystemProperties systemProperties = new SystemProperties();
    private static CoverageService instance;
    private final DefaultCoverageInstructionEventHandler defaultCoverageInstructionEventHandler;
    private final CoverageInstructionInjector coverageInstructionInjector;
    private final CoverageInstructionListener coverageInstructionListener;

    public CoverageService() {
        defaultCoverageInstructionEventHandler = new DefaultCoverageInstructionEventHandler();
        coverageInstructionInjector = new CoverageInstructionInjector(defaultCoverageInstructionEventHandler);
        coverageInstructionListener = new CoverageInstructionListener(defaultCoverageInstructionEventHandler);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                Report report = getReport();
                String reportDir = systemProperties.getReportDir().or(DEFAULT_REPORT_DIR);
                if (systemProperties.isBinReportSavingEnabledOnShutdown()) {
                    ReportRepository reportRepository = new ReportRepository(new File(reportDir, "bin"));
                    reportRepository.save(report);
                }
                if (systemProperties.isHtmlReportSavingEnabledOnShutdown()) {
                    new FileReportPrinter(new File(reportDir, "html")).print(report);
                }
            }
        });
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
