package org.xquery.saxon.coverage.trace;

import net.sf.saxon.expr.XPathContext;
import net.sf.saxon.expr.flwor.ClauseInfo;
import net.sf.saxon.trace.InstructionInfo;

public abstract class AbstractCoverageExpressionListener extends TraceListenerAdapter {

    @Override
    public void enter(InstructionInfo instruction, XPathContext context) {
        if (instruction instanceof CoverageExpression) {
            enter((CoverageExpression) instruction);
        } else if (instruction instanceof ClauseInfo) {
            // TODO: investigate
        } else {
            // TODO: investigate
        }
    }

    protected abstract void enter(CoverageExpression expression);
}
