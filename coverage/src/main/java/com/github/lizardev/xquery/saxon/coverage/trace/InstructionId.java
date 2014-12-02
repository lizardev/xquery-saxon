package com.github.lizardev.xquery.saxon.coverage.trace;

import java.util.UUID;

public final class InstructionId {
    private final Object id;

    private InstructionId(Object id) {
        this.id = id;
    }

    public static InstructionId uniqueInstructionId() {
        return new InstructionId(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InstructionId that = (InstructionId) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
