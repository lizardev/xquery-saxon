package org.xquery.saxon.coverage.report;

import org.xquery.saxon.coverage.ModuleUri;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Report {

    private Map<ModuleUri, ModuleReport> moduleReports = new HashMap<>();

    public void addModuleReport(ModuleReport moduleReport) {
        moduleReports.put(moduleReport.getModuleUri(), moduleReport);
    }

    public ModuleReport getModuleReport(ModuleUri moduleUri) {
        return moduleReports.get(moduleUri);
    }

    public Collection<ModuleReport> getModuleReports() {
        return moduleReports.values();
    }
}
