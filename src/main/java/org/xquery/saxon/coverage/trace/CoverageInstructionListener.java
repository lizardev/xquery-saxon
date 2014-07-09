package org.xquery.saxon.coverage.trace;

public class CoverageInstructionListener extends AbstractCoverageInstructionListener {

    private final CoverageInstructionEventHandler eventHandler;

    public CoverageInstructionListener(CoverageInstructionEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    protected void enter(Identifier identifier) {
        eventHandler.handle(new CoverageInstructionInvokedEvent(identifier));
    }
}
