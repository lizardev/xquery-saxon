package com.github.lizardev.xquery.saxon.coverage.report;

import com.github.lizardev.xquery.saxon.coverage.ModuleUri;
import com.google.common.collect.FluentIterable;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Report implements Serializable {

    private static final long serialVersionUID = 1;
    private final String id = UUID.randomUUID().toString();

    private Map<ModuleUri, ModuleReport> moduleReports = new HashMap<>();

    public void merge(Report report) {
        for (ModuleReport moduleReport : report.getModuleReports()) {
            addOrMergeModuleReport(moduleReport);
        }
    }

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

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Report)) {
            return false;
        }
        Report report = (Report) o;
        return Objects.equals(id, report.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
