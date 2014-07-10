package org.xquery.saxon.coverage.trace;

import net.sf.saxon.expr.Container;
import net.sf.saxon.expr.XPathContext;
import net.sf.saxon.expr.flwor.Clause;
import net.sf.saxon.expr.flwor.ExpressionProcessor;
import net.sf.saxon.expr.flwor.TuplePull;
import net.sf.saxon.expr.flwor.TuplePush;
import net.sf.saxon.om.NamespaceResolver;
import net.sf.saxon.trace.ExpressionPresenter;
import net.sf.saxon.trans.XPathException;

class CoverageClause extends Clause implements CoverageInstruction {
    private final Clause target;
    private final NamespaceResolver namespaceResolver;
    private final Container container;

    public CoverageClause(Clause target, NamespaceResolver namespaceResolver, Container container) {
        this.target = target;
        this.namespaceResolver = namespaceResolver;
        this.container = container;
    }

    public NamespaceResolver getNamespaceResolver() {
        return namespaceResolver;
    }

    @Override
    public Identifier getIdentifier() {
        return new Identifier(this);
    }

    @Override
    public int getLineNumber() {
        return target.getLocationId();
    }

    @Override
    public CoverageClause copy() {
        return new CoverageClause(target, namespaceResolver, container);
    }

    @Override
    public TuplePull getPullStream(TuplePull base, XPathContext context) {
        return new CoverageClausePull(base, this, container);
    }

    @Override
    public TuplePush getPushStream(TuplePush destination, XPathContext context) {
        return new CoverageClausePush(destination, this, container);
    }

    @Override
    public void processSubExpressions(ExpressionProcessor processor) throws XPathException {
    }

    @Override
    public void explain(ExpressionPresenter out) {
        out.startElement("trace");
        out.endElement();
    }

    @Override
    public int getClauseKey() {
        return TRACE;
    }


    @Override
    public String toString() {
        return String.format("lineNumber=%d, clause=trace", getLineNumber());
    }
}
