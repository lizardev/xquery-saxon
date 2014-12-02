package com.github.lizardev.xquery.saxon.coverage.report;

import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.github.lizardev.xquery.saxon.coverage.report.Freemarker.renderTemplate;
import static com.github.lizardev.xquery.saxon.coverage.util.FileUtils.readLines;

public class HtmlModuleReportGenerator {

    public String generate(ModuleReport moduleReport) {
        List<String> sourceCodeLines = readLines(moduleReport.getModuleUri().getUri());
        List<LineReport> lineReports = new ArrayList<>();
        for (int i = 1; i <= sourceCodeLines.size(); i++) {
            String sourceCodeLine = sourceCodeLines.get(i - 1);
            LineReport lineReport = moduleReport.getLineReport(i);
            if (lineReport == null) {
                lineReports.add(new LineReport(i, sourceCodeLine));
            } else {
                lineReports.add(new LineReport(i, lineReport.getInstructionReports(), sourceCodeLine));
            }
        }
        Map<String, ?> params = ImmutableMap.of("lineReports", lineReports);
        return renderTemplate("/org/xquery/saxon/coverage/report/ModuleReport.ftl", params);
    }
}
