package org.xquery.saxon.coverage.trace;

import net.sf.saxon.expr.Expression;
import net.sf.saxon.expr.instruct.TraceExpression;
import net.sf.saxon.expr.parser.ExpressionVisitor;
import org.xquery.saxon.coverage.Construct;

import static org.xquery.saxon.coverage.trace.InstructionId.uniqueInstructionId;

public class CoverageExpression extends TraceExpression implements CoverageInstruction {

    private final InstructionId instructionId = uniqueInstructionId();

    public CoverageExpression(Expression child) {
        super(child);
    }

    @Override
    public String toString() {
        return String.format("lineNumber=%d, constructType=%s, exp=%s", getLineNumber(), Construct.constructToString(getConstructType()), super.toString());
    }

    @Override
    public Expression simplify(ExpressionVisitor visitor) {
        return this;
    }

    @Override
    public InstructionId getInstructionId() {
        return instructionId;
    }
}
