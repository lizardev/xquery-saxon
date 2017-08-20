package com.github.lizardev.xquery.saxon.coverage

import com.github.lizardev.xquery.saxon.coverage.report.InstructionReport
import com.github.lizardev.xquery.saxon.coverage.report.LineReport
import com.github.lizardev.xquery.saxon.coverage.report.ModuleReport
import com.github.lizardev.xquery.saxon.coverage.report.Report

import static com.github.lizardev.xquery.saxon.coverage.TestConstants.ONE_LINE_MODULE

class TestObjects {

    static String id() {
        UUID.randomUUID().toString()
    }

    static Report someReport() {
        Report report = new Report()
        report.addOrMergeModuleReport(new ModuleReport(ONE_LINE_MODULE, [new LineReport(1,
                [new InstructionReport("some-instruction-${id()}", true)])]))
        report
    }
}
