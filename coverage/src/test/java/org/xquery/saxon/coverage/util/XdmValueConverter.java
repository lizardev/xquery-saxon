package org.xquery.saxon.coverage.util;

import net.sf.saxon.s9api.XdmAtomicValue;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XdmValue;

@SuppressWarnings("unchecked")
public class XdmValueConverter {

    public <T> T convert(XdmValue value) {
        if (value instanceof XdmAtomicValue) {
            XdmAtomicValue atomicValue = (XdmAtomicValue) value;
            return (T) atomicValue.getValue();
        } else if (value instanceof XdmNode) {
            XdmNode node = (XdmNode) value;
            return (T) node.toString();
        } else {
            return (T) value;
        }
    }
}
