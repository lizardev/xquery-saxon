package org.xquery.saxon.coverage.report;

import com.google.common.collect.FluentIterable;
import org.xquery.saxon.coverage.ModuleUri;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
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

    public List<ModuleReport> getModuleReports() {
        return FluentIterable.from(moduleReports.values())
                .toSortedList(new Comparator<ModuleReport>() {
                    @Override public int compare(ModuleReport o1, ModuleReport o2) {
                        return o1.getModuleUri().toString().compareTo(o2.getModuleUri().toString());
                    }
                });
    }
}
