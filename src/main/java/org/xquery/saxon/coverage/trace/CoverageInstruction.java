package org.xquery.saxon.coverage.trace;

public interface CoverageInstruction {
	Identifier getIdentifier();

	int getLineNumber();
}
