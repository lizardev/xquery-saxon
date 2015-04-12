package com.github.lizardev.xquery.saxon.coverage.stub;

import com.github.lizardev.xquery.saxon.support.trace.TraceListenerAdapter;
import net.sf.saxon.expr.XPathContext;
import net.sf.saxon.trace.InstructionInfo;

public class TraceListenerStub extends TraceListenerAdapter {

    private int numberOfEnteredInstructions;

    @Override public void enter(InstructionInfo instruction, XPathContext context) {
        super.enter(instruction, context);
        numberOfEnteredInstructions++;
    }

    public int numberOfEnteredInstructions() {
        return numberOfEnteredInstructions;
    }
}
