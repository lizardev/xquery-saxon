package org.xquery.saxon.coverage.report;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Report {

    Map<String, ModuleReport> moduleReports = new HashMap<String, ModuleReport>();

    public void addModuleReport(ModuleReport moduleReport) {
        moduleReports.put(moduleReport.getModule(), moduleReport);
    }

    public ModuleReport getModuleReport(String module) {
        return moduleReports.get(module);
    }

    public Collection<ModuleReport> getModuleReports() {
        return moduleReports.values();
    }
}
