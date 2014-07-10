package org.xquery.saxon.coverage.trace;

import net.sf.saxon.Controller;
import net.sf.saxon.expr.Container;
import net.sf.saxon.expr.XPathContext;
import net.sf.saxon.expr.flwor.ClauseInfo;
import net.sf.saxon.expr.flwor.TuplePush;
import net.sf.saxon.trans.XPathException;

public class CoverageClausePush extends TuplePush {

    private final TuplePush destination;
    private final CoverageClause coverageClause;
    private final Container container;

    public CoverageClausePush(TuplePush destination, CoverageClause coverageClause, Container container) {
        this.destination = destination;
        this.coverageClause = coverageClause;
        this.container = container;
    }

    @Override
    public void processTuple(XPathContext context) throws XPathException {
        Controller controller = context.getController();
        if (controller.isTracing()) {
            ClauseInfo baseInfo = new ClauseInfo(coverageClause, container);
            baseInfo.setNamespaceResolver(coverageClause.getNamespaceResolver());
            controller.getTraceListener().enter(baseInfo, context);
            destination.processTuple(context);
            controller.getTraceListener().leave(baseInfo);
        } else {
            destination.processTuple(context);
        }
    }

    @Override
    public void close() throws XPathException {
        destination.close();
    }
}