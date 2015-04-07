package com.github.lizardev.xquery.saxon.coverage.trace;

import net.sf.saxon.expr.Expression;
import net.sf.saxon.expr.instruct.TraceExpression;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Objects;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class CoverageExpressionTest {

    @Mock private Expression expression;
    @Mock private CoverageInstructionEventHandler handler;

    @Test
    public void shouldRemoveObjectsAddressInToString() {
        CoverageExpression coverageExpression = new CoverageExpression(new TraceExpression(expression) {
            @Override
            public String toString() {
                return Objects.toString(new ClassWithNoToStringImplementation());
            }
        }, handler);

        String result = coverageExpression.toString();
        
        assertThat(result).isEqualTo("com.github.lizardev.xquery.saxon.coverage.trace.CoverageExpressionTest$ClassWithNoToStringImplementation");
    }

    @Test
    public void shouldNotTryToRemoveObjectsAddressInToStringIfItIsAbsent() {
        String stringRepresentation = "stringRepresentation";
        given(expression.toString()).willReturn(stringRepresentation);
        CoverageExpression coverageExpression = new CoverageExpression(new TraceExpression(expression), handler);

        String result = coverageExpression.toString();

        assertThat(result).isEqualTo(format("TraceExpression(%s)", stringRepresentation));
    }


    private static class ClassWithNoToStringImplementation {
    }
}