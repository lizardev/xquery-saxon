package com.github.lizardev.xquery.saxon.coverage.trace;

import com.github.lizardev.xquery.saxon.support.trace.TraceExpressionComponent;
import net.sf.saxon.expr.Expression;
import net.sf.saxon.expr.parser.ExpressionVisitor;
import net.sf.saxon.trans.XPathException;

import static com.github.lizardev.xquery.saxon.coverage.trace.InstructionId.uniqueInstructionId;

@SuppressWarnings("serial")
public class CoverageExpression extends TraceExpressionComponent {

    private final InstructionId instructionId = uniqueInstructionId();
    private final CoverageInstructionEventHandler eventHandler;

    public CoverageExpression(Expression expression, int depth, CoverageInstructionEventHandler eventHandler) {
        super(expression, depth);
        this.eventHandler = eventHandler;
    }

    @Override
    public String toString() {
        return getChild().toString();
    }

    @Override
    public Expression simplify(ExpressionVisitor visitor) throws XPathException {
        Expression simplified = super.simplify(visitor);
        if (simplified != this) {
            eventHandler.handle(new CoverageInstructionSimplifiedEvent(instructionId));
        }
        return simplified;
    }

    public InstructionId getInstructionId() {
        return instructionId;
    }
}
