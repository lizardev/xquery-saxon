package com.github.lizardev.xquery.saxon.coverage.report;

import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;

import static com.github.lizardev.xquery.saxon.coverage.report.Freemarker.renderTemplate;

public class HtmlModuleReportIndexGenerator {

    public String generate(List<ModuleReportReference> moduleReportReferences) {
        Map<String, ?> params = ImmutableMap.of("moduleReportReferences", moduleReportReferences);
        return renderTemplate("/com/github/lizardev/xquery/saxon/coverage/report/ModuleReportIndex.ftl", params);
    }
}
