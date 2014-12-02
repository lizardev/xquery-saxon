package com.github.lizardev.xquery.saxon.coverage.util;

import com.google.common.collect.ImmutableList;
import net.sf.saxon.s9api.QName;

import java.util.ArrayList;
import java.util.List;

public class ExecutionContext {

    private List<ExternalVariable> externalVariables = new ArrayList<>();

    public List<ExternalVariable> getExternalVariables() {
        return ImmutableList.copyOf(externalVariables);
    }

    public static Builder executionContext() {
        return new Builder();
    }

    public static class Builder {

        private ExecutionContext executionContext = new ExecutionContext();

        public Builder withExternalVariable(String name, boolean value) {
            executionContext.externalVariables.add(new ExternalVariable(new QName(name), value));
            return this;
        }

        public ExecutionContext build() {
            return executionContext;
        }
    }
}
