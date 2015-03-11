package com.github.lizardev.xquery.saxon.coverage;

public final class TestConstants {

    public static final ModuleUri TOUR_MODULE = ModuleUri.fromResourceName("/Tour.xq");
    public static final ModuleUri ONE_LINE_MODULE = ModuleUri.fromResourceName("/OneLine.xq");
    public static final ModuleUri FUNCTIONS_MODULE = ModuleUri.fromResourceName("/Functions.xq");
    public static final ModuleUri TWO_BRANCHES_MODULE = ModuleUri.fromResourceName("/TwoBranches.xq");
    public static final ModuleUri IMPORT_MAIN_MODULE = ModuleUri.fromResourceName("/import/Main.xq");
    public static final ModuleUri IMPORT_LIBRARY_MODULE = ModuleUri.fromResourceName("/import/Library.xq");
    public static final ModuleUri DUPLICATED_TRACE_EXPRESSION_MODULE = ModuleUri.fromResourceName("/DuplicatedTraceExpression.xq");

    private TestConstants() {
    }
}
