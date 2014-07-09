package org.xquery.saxon.coverage.trace;

import net.sf.saxon.expr.Container;
import net.sf.saxon.expr.flwor.Clause;
import net.sf.saxon.expr.flwor.TraceClause;
import net.sf.saxon.om.NamespaceResolver;

class CoverageClause extends TraceClause implements CoverageInstruction {
	private final Clause clause;

	public CoverageClause(Clause clause, NamespaceResolver nsResolver, Container container) {
		super(clause, nsResolver, container);
		this.clause = clause;
	}

	@Override
	public Identifier getIdentifier() {
		return new Identifier(clause);
	}

	@Override
	public int getLineNumber() {
		return clause.getLocationId();
	}

	@Override
	public String toString() {
		return String.format("lineNumber=%d, clause=%s", getLineNumber(), super.toString());
	}
}
