package com.github.lizardev.xquery.saxon.coverage.trace;

import net.sf.saxon.expr.Expression;
import net.sf.saxon.expr.instruct.TraceExpression;
import net.sf.saxon.expr.parser.ExpressionVisitor;

import static com.github.lizardev.xquery.saxon.coverage.trace.InstructionId.uniqueInstructionId;

public class CoverageExpression extends TraceExpression {

    private final InstructionId instructionId = uniqueInstructionId();
    private final Expression child;

    public CoverageExpression(Expression child) {
        super(child);
        this.child = child;
    }

    @Override
    public String toString() {
        return child.toString();
    }

    @Override
    public Expression simplify(ExpressionVisitor visitor) {
        return this;
    }

    public InstructionId getInstructionId() {
        return instructionId;
    }
}