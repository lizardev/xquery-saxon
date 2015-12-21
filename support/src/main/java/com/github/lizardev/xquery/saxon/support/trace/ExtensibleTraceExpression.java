package com.github.lizardev.xquery.saxon.support.trace;

import net.sf.saxon.expr.Expression;
import net.sf.saxon.expr.instruct.TraceExpression;

import static com.github.lizardev.xquery.saxon.support.util.ReflectionUtils.setFieldValue;

@SuppressWarnings("serial")
public abstract class ExtensibleTraceExpression extends TraceExpression {

    public ExtensibleTraceExpression(Expression child) {
        super(child);
    }

    protected Expression getChild() {
        return getChildExpression();
    }

    protected void setChild(Expression child) {
        setFieldValue(this, "child", child);
    }
}
