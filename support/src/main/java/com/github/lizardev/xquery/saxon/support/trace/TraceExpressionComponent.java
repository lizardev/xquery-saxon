package com.github.lizardev.xquery.saxon.support.trace;

import net.sf.saxon.expr.Expression;
import net.sf.saxon.expr.parser.ExpressionVisitor;
import net.sf.saxon.trans.XPathException;

@SuppressWarnings("serial")
public class TraceExpressionComponent extends ExtensibleTraceExpression {

    private final int depth;

    public TraceExpressionComponent(Expression child, int depth) {
        super(child);
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }

    public Expression simplify(ExpressionVisitor visitor) throws XPathException {
        Expression child = getChild();
        Expression simplified = visitor.simplify(child);

        if (!simplified.equals(child)) {
            setChild(simplified);
        }
        if (simplified instanceof TraceExpressionComponent) {
            TraceExpressionComponent childAsComponent = (TraceExpressionComponent) simplified;
            if (getDepth() <= childAsComponent.getDepth()) {
                return simplified;
            }
        }
        return this;
    }

    @Override public String toString() {
        return getChild().toString();
    }
}
