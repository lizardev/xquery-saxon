package com.github.lizardev.xquery.saxon.coverage.trace;

import net.sf.saxon.Controller;
import net.sf.saxon.expr.Container;
import net.sf.saxon.expr.XPathContext;
import net.sf.saxon.expr.flwor.ClauseInfo;
import net.sf.saxon.expr.flwor.TuplePull;
import net.sf.saxon.trans.XPathException;

public class CoverageClausePull extends TuplePull {

    private final TuplePull base;
    private final CoverageClause coverageClause;
    private final Container container;

    public CoverageClausePull(TuplePull base, CoverageClause coverageClause, Container container) {
        this.base = base;
        this.coverageClause = coverageClause;
        this.container = container;
    }

    @Override
    public boolean nextTuple(XPathContext context) throws XPathException {
        Controller controller = context.getController();
        if (controller.isTracing()) {
            ClauseInfo baseInfo = new ClauseInfo(coverageClause, container);
            baseInfo.setNamespaceResolver(coverageClause.getNamespaceResolver());
            controller.getTraceListener().enter(baseInfo, context);
            boolean b = base.nextTuple(context);
            controller.getTraceListener().leave(baseInfo);
            return b;
        } else {
            return base.nextTuple(context);
        }
    }

    @Override
    public void close() {
        base.close();
    }
}