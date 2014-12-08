package com.github.lizardev.xquery.saxon.coverage.trace;

import com.github.lizardev.xquery.saxon.support.trace.TraceListenerAdapter;
import net.sf.saxon.expr.XPathContext;
import net.sf.saxon.expr.flwor.Clause;
import net.sf.saxon.expr.flwor.ClauseInfo;
import net.sf.saxon.trace.InstructionInfo;

public abstract class AbstractCoverageInstructionListener extends TraceListenerAdapter {

    @Override
    public void enter(InstructionInfo instruction, XPathContext context) {
        if (instruction instanceof CoverageExpression) {
            enter(((CoverageExpression) instruction).getInstructionId());
        } else if (instruction instanceof ClauseInfo) {
            Clause clause = ((ClauseInfo) instruction).getClause();
            if (clause instanceof CoverageClause) {
                enter(((CoverageClause) clause).getInstructionId());
            } else {
                throw new IllegalStateException("unknown clause " + clause);
            }
        } else {
            throw new IllegalStateException("unknown instruction " + instruction);
        }
    }

    protected abstract void enter(InstructionId instructionId);
}
