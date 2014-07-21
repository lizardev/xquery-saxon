package org.xquery.saxon.coverage.trace;

import net.sf.saxon.expr.XPathContext;
import net.sf.saxon.expr.flwor.ClauseInfo;
import net.sf.saxon.trace.InstructionInfo;

public abstract class AbstractCoverageInstructionListener extends TraceListenerAdapter {

    @Override
    public void enter(InstructionInfo instruction, XPathContext context) {
        if (instruction instanceof CoverageExpression) {
            enter(new Identifier(instruction));
        } else if (instruction instanceof ClauseInfo) {
            enter(new Identifier(((ClauseInfo) instruction).getClause()));
        } else {
            throw new IllegalStateException("unknown instruction");
        }
    }

    protected abstract void enter(Identifier instruction);
}
