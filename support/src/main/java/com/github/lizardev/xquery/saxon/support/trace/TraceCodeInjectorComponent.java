package com.github.lizardev.xquery.saxon.support.trace;

import net.sf.saxon.expr.Expression;
import net.sf.saxon.expr.Literal;
import net.sf.saxon.expr.StaticContext;
import net.sf.saxon.expr.instruct.TraceExpression;
import net.sf.saxon.om.StructuredQName;
import net.sf.saxon.trace.TraceCodeInjector;

public class TraceCodeInjectorComponent extends TraceCodeInjector {

    public Expression inject(Expression exp, StaticContext env, int construct, StructuredQName qName, int depth) {
        if (exp instanceof Literal) {
            return exp;
        }
        TraceExpression trace = new TraceExpressionComponent(exp, depth);
        trace.setNamespaceResolver(env.getNamespaceResolver());
        trace.setConstructType(construct);
        trace.setObjectName(qName);
        return trace;
    }
}
