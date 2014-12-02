package com.github.lizardev.xquery.saxon.coverage.collect;

import java.util.UUID;

public class ModuleId {
    private final Object id;

    public ModuleId(Object id) {
        this.id = id;
    }

    public static ModuleId uniqueModuleId() {
        return new ModuleId(UUID.randomUUID().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ModuleId moduleId = (ModuleId) o;
        return id.equals(moduleId.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
