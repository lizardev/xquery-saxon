package com.github.lizardev.xquery.saxon.coverage.trace;

import net.sf.saxon.expr.Expression;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class StableExpressionToStringConverterTest {

    @Mock private Expression expression;
    private StableExpressionToStringConverter stableExpressionToStringConverter = new StableExpressionToStringConverter();

    @Test
    public void shouldRemoveHashCodeSuffixWhenStringRepresentSaxonicaClassWithHashCodeSuffix() {
        given(expression.toString()).willReturn("com.saxonica.functions.hof.CallableFunctionItem@6bee793f");

        String expressionAsString = stableExpressionToStringConverter.toString(expression);

        assertThat(expressionAsString).isEqualTo("com.saxonica.functions.hof.CallableFunctionItem");
    }

    @Test
    public void shouldNotCovertWhenStringDoesNotRepresentClassName() {
        String givenExpressionAsString = "substring-after($nextStep,'@')";
        given(expression.toString()).willReturn(givenExpressionAsString);

        String expressionAsString = stableExpressionToStringConverter.toString(expression);

        assertThat(expressionAsString).isEqualTo(givenExpressionAsString);
    }
}