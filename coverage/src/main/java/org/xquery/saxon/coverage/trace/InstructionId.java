package org.xquery.saxon.coverage.trace;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.UUID;

public class InstructionId {
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

        return new EqualsBuilder().append(id, ((InstructionId) o).id).build();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).build();
    }
}
