package com.github.lizardev.xquery.saxon.coverage.report;

public final class ModuleReportFile {
    private final String name;
    private final String path;

    public ModuleReportFile(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}