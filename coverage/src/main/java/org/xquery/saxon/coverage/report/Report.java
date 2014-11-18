package org.xquery.saxon.coverage.report;

import org.xquery.saxon.coverage.ModuleUri;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Report {

    private Map<ModuleUri, ModuleReport> moduleReports = new HashMap<>();

    public void addOrMergeModuleReport(ModuleReport moduleReport) {
        ModuleUri moduleUri = moduleReport.getModuleUri();
        ModuleReport actualModuleReport = moduleReports.get(moduleUri);
        if (actualModuleReport == null) {
            actualModuleReport = moduleReport;
        } else {
            actualModuleReport = actualModuleReport.merge(moduleReport);
        }
        moduleReports.put(moduleUri, actualModuleReport);
    }

    public ModuleReport getModuleReport(ModuleUri moduleUri) {
        return moduleReports.get(moduleUri);
    }

    public Collection<ModuleReport> getModuleReports() {
        return moduleReports.values();
    }
}
