package org.xquery.saxon.coverage.trace;

public class CoverageInstructionListener extends AbstractCoverageInstructionListener {

    private final CoverageInstructionEventHandler eventHandler;

    public CoverageInstructionListener(CoverageInstructionEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    protected void enter(InstructionId instructionId) {
        eventHandler.handle(new CoverageInstructionInvokedEvent(instructionId));
    }
}
