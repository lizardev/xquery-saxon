package org.xquery.saxon.coverage.trace;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Identifier {
	private final Object id;

	public Identifier(Object id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Identifier{id='" + id + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		return new EqualsBuilder().append(id, ((Identifier) o).id).build();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).build();
	}
}
