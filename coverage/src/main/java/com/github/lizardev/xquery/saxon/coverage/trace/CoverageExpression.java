package com.github.lizardev.xquery.saxon.coverage.trace;

import net.sf.saxon.expr.Expression;
import net.sf.saxon.expr.instruct.TraceExpression;
import net.sf.saxon.expr.parser.ExpressionVisitor;
import net.sf.saxon.trans.XPathException;

import static com.github.lizardev.xquery.saxon.coverage.trace.InstructionId.uniqueInstructionId;
import static com.github.lizardev.xquery.saxon.coverage.util.ReflectionUtils.getFieldValue;

@SuppressWarnings("serial")
public class CoverageExpression extends TraceExpression {

    private final InstructionId instructionId = uniqueInstructionId();
    private final CoverageInstructionEventHandler eventHandler;

    public CoverageExpression(Expression child, CoverageInstructionEventHandler eventHandler) {
        super(child);
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

    private Expression getChild() {
        return getFieldValue(this, "child");
    }
}
