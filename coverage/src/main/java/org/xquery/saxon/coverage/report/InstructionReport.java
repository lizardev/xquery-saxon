package org.xquery.saxon.coverage.report;

import org.apache.commons.lang3.builder.ToStringBuilder;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class InstructionReport {

	private final String instruction;
	private final boolean covered;

	public InstructionReport(String instruction, boolean covered) {
		this.instruction = instruction;
		this.covered = covered;
	}

	public boolean isCovered() {
		return covered;
	}

	public String getInstruction() {
		return instruction;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, SHORT_PREFIX_STYLE).append("instruction", instruction).append("covered", covered).build();
	}
}
