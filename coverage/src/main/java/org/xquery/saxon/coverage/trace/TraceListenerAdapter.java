package org.xquery.saxon.coverage.trace;

import net.sf.saxon.Controller;
import net.sf.saxon.expr.XPathContext;
import net.sf.saxon.lib.TraceListener;
import net.sf.saxon.om.Item;
import net.sf.saxon.trace.InstructionInfo;

import java.io.PrintStream;

public class TraceListenerAdapter implements TraceListener {

    @Override
    public void setOutputDestination(PrintStream stream) {

    }

    @Override
    public void open(Controller controller) {

    }

    @Override
    public void close() {

    }

    @Override
    public void enter(InstructionInfo instruction, XPathContext context) {

    }

    @Override
    public void leave(InstructionInfo instruction) {

    }

    @Override
    public void startCurrentItem(Item currentItem) {

    }

    @Override
    public void endCurrentItem(Item currentItem) {

    }
}
