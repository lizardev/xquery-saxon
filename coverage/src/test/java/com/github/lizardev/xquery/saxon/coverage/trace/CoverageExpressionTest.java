package com.github.lizardev.xquery.saxon.coverage.trace;

import net.sf.saxon.expr.Expression;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class CoverageExpressionTest {

    private static final int DEPTH = 0;
    @Mock private Expression expression;
    @Mock private CoverageInstructionEventHandler handler;

    @Test
    public void shouldRemoveObjectsAddressInToString() {
        given(expression.toString()).willReturn(Objects.toString(new ClassWithNoToStringImplementation()));
        CoverageExpression coverageExpression = new CoverageExpression(expression, DEPTH, handler);

        String result = coverageExpression.toString();

        assertThat(result).isEqualTo("com.github.lizardev.xquery.saxon.coverage.trace.CoverageExpressionTest$ClassWithNoToStringImplementation");
    }

    @Test
    public void shouldNotTryToRemoveObjectsAddressInToStringIfItIsAbsent() {
        String stringRepresentation = "stringRepresentation";
        given(expression.toString()).willReturn(stringRepresentation);
        CoverageExpression coverageExpression = new CoverageExpression(expression, DEPTH, handler);

        String result = coverageExpression.toString();

        assertThat(result).isEqualTo(stringRepresentation);
    }

    @Test
    public void shouldHandleExpressionWithAtCharacter() {
        String stringRepresentation = "substring-after($nextStep,'@')";
        given(expression.toString()).willReturn(stringRepresentation);
        CoverageExpression coverageExpression = new CoverageExpression(expression, DEPTH, handler);

        String result = coverageExpression.toString();

        assertThat(result).isEqualTo(stringRepresentation);
    }


    private static class ClassWithNoToStringImplementation {
    }
}