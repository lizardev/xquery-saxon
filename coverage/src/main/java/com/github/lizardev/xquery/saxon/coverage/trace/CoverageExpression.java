package com.github.lizardev.xquery.saxon.coverage.trace;

import com.github.lizardev.xquery.saxon.support.trace.ExtensibleTraceExpression;
import net.sf.saxon.expr.Expression;
import net.sf.saxon.expr.parser.ExpressionVisitor;
import net.sf.saxon.trans.XPathException;

import static com.github.lizardev.xquery.saxon.coverage.trace.InstructionId.uniqueInstructionId;

@SuppressWarnings("serial")
public class CoverageExpression extends ExtensibleTraceExpression {

    private final InstructionId instructionId = uniqueInstructionId();
    private final CoverageInstructionEventHandler eventHandler;

    public CoverageExpression(Expression child, CoverageInstructionEventHandler eventHandler) {
        super(child);
        this.eventHandler = eventHandler;
    }

    @Override
    public String toString() {
        String childToString = getChild().toString();
        if (childToString.contains("@")) {
            return removeObjectsAddress(childToString);
        } else {
            return childToString;
        }
    }

    private String removeObjectsAddress(String input) {
        return input.substring(0, input.indexOf('@'));
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
