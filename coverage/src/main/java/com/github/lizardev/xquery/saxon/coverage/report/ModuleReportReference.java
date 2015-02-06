package com.github.lizardev.xquery.saxon.coverage.report;

public final class ModuleReportReference {
    private final String name;
    private final String path;
    private final double lineCoverage;

    public ModuleReportReference(String name, String path, double lineCoverage) {
        this.name = name;
        this.path = path;
        this.lineCoverage = lineCoverage;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public double getLineCoverage() {
        return lineCoverage;
    }
}