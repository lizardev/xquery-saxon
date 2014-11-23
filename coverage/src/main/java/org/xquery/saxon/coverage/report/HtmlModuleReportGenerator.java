package org.xquery.saxon.coverage.report;

import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.xquery.saxon.coverage.report.Freemarker.renderTemplate;
import static org.xquery.saxon.coverage.util.FileUtils.readLines;

public class HtmlModuleReportGenerator {

    public String generate(ModuleReport moduleReport) {
        List<String> lines = readLines(moduleReport.getModuleUri().getUri());
        List<LineReport> lineReports = new ArrayList<>();
        for (int i = 1; i <= lines.size(); i++) {
            LineReport lineReport = moduleReport.getLineReport(i);
            lineReports.add(lineReport);
        }
        Map<String, ?> params = ImmutableMap.of("lineReports", lineReports, "lines", lines);
        return renderTemplate("/org/xquery/saxon/coverage/report/ModuleReport.ftl", params);
    }
}
