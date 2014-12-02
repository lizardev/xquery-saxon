package com.github.lizardev.xquery.saxon.coverage.trace;

public interface CoverageInstructionEventHandler {

    void handle(CoverageInstructionCreatedEvent event);

    void handle(CoverageInstructionInvokedEvent event);
}
