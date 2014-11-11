package org.xquery.saxon.coverage.util;

import net.sf.saxon.s9api.QName;

public class ExternalVariable {

    private QName name;
    private boolean value;

    public ExternalVariable(QName name, boolean value) {
        this.name = name;
        this.value = value;
    }

    public QName getName() {
        return name;
    }

    public boolean getValue() {
        return value;
    }
}
