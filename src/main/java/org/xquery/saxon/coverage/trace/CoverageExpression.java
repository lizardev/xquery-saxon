package org.xquery.saxon.coverage.trace;

import net.sf.saxon.expr.Expression;
import net.sf.saxon.expr.instruct.TraceExpression;
import net.sf.saxon.expr.parser.ExpressionVisitor;
import net.sf.saxon.trans.XPathException;
import org.xquery.saxon.coverage.Construct;

public class CoverageExpression extends TraceExpression implements CoverageInstruction {

	public CoverageExpression(Expression child) {
		super(child);
	}

	@Override
	public String toString() {
		return String.format("lineNumber=%d, constructType=%s, exp=%s", getLineNumber(), Construct.constructToString(getConstructType()), super.toString());
	}

	@Override
	public Expression simplify(ExpressionVisitor visitor) throws XPathException {
		return this;
	}

	@Override
	public Identifier getIdentifier() {
		return new Identifier(this);
	}
}
