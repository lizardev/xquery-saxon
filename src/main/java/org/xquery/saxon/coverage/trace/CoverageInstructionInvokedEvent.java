package org.xquery.saxon.coverage.trace;

public class CoverageInstructionInvokedEvent {

	private Identifier identifier;

	public CoverageInstructionInvokedEvent(Identifier identifier) {
        this.identifier = identifier;
    }

	public Identifier getIdentifier() {
		return identifier;
	}
}
