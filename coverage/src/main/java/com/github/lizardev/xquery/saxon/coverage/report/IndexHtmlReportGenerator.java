package com.github.lizardev.xquery.saxon.coverage.report;

import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;

import static com.github.lizardev.xquery.saxon.coverage.report.Freemarker.renderTemplate;

public class IndexHtmlReportGenerator {

    public String generate(List<ModuleReportFile> reportFiles) {
        Map<String, ?> params = ImmutableMap.of("reportFiles", reportFiles);
        return renderTemplate("/com/github/lizardev/xquery/saxon/coverage/report/ReportAggregator.ftl", params);
    }
}
