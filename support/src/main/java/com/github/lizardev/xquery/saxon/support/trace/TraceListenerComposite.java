package com.github.lizardev.xquery.saxon.support.trace;

import com.google.common.collect.Lists;
import net.sf.saxon.Controller;
import net.sf.saxon.expr.XPathContext;
import net.sf.saxon.lib.Logger;
import net.sf.saxon.lib.TraceListener;
import net.sf.saxon.om.Item;
import net.sf.saxon.trace.InstructionInfo;

import java.util.List;

public class TraceListenerComposite implements TraceListener {

    private List<TraceListener> traceListeners;

    public TraceListenerComposite(List<TraceListener> traceListeners) {
        this.traceListeners = traceListeners;
    }

    @Override
    public void setOutputDestination(Logger logger) {
        for (TraceListener traceListener : traceListeners) {
            traceListener.setOutputDestination(logger);
        }
    }

    @Override
    public void open(Controller controller) {
        for (TraceListener traceListener : traceListeners) {
            traceListener.open(controller);
        }
    }

    @Override
    public void close() {
        for (TraceListener traceListener : Lists.reverse(traceListeners)) {
            traceListener.close();
        }
    }

    @Override
    public void enter(InstructionInfo instruction, XPathContext context) {
        for (TraceListener traceListener : traceListeners) {
            traceListener.enter(instruction, context);
        }
    }

    @Override
    public void leave(InstructionInfo instruction) {
        for (TraceListener traceListener : Lists.reverse(traceListeners)) {
            traceListener.leave(instruction);
        }
    }

    @Override
    public void startCurrentItem(Item currentItem) {
        for (TraceListener traceListener : traceListeners) {
            traceListener.startCurrentItem(currentItem);
        }
    }

    @Override
    public void endCurrentItem(Item currentItem) {
        for (TraceListener traceListener : Lists.reverse(traceListeners)) {
            traceListener.endCurrentItem(currentItem);
        }
    }
}
