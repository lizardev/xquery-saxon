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

    private static final String CLASS_NAME = "com.saxonica.functions.hof.CallableFunctionItem";
    private static final String CLASS_NAME_WITH_HASH_CODE_SUFFIX = CLASS_NAME + "@6bee793f";
    private static final String EXPRESSION_WITHOUT_CLASS_NAME = "substring-after($nextStep,'@')";
    @Mock private Expression expression;
    private StableExpressionToStringConverter stableExpressionToStringConverter = new StableExpressionToStringConverter();

    @Test
    public void shouldRemoveHashCodeSuffixWhenStringRepresentSaxonicaClassWithHashCodeSuffix() {
        given(expression.toString()).willReturn(CLASS_NAME_WITH_HASH_CODE_SUFFIX);

        String expressionAsString = stableExpressionToStringConverter.toString(expression);

        assertThat(expressionAsString).isEqualTo(CLASS_NAME);
    }

    @Test
    public void shouldNotCovertWhenStringDoesNotRepresentClassName() {
        given(expression.toString()).willReturn(EXPRESSION_WITHOUT_CLASS_NAME);

        String expressionAsString = stableExpressionToStringConverter.toString(expression);

        assertThat(expressionAsString).isEqualTo(EXPRESSION_WITHOUT_CLASS_NAME);
    }

    @Test
    public void shouldReturnStoredValueWhenTheSameExpressionIsPassedSecondTimeInARow() {
        given(expression.toString())
                .willReturn(CLASS_NAME_WITH_HASH_CODE_SUFFIX)
                .willReturn(EXPRESSION_WITHOUT_CLASS_NAME);

        assertThat(stableExpressionToStringConverter.toString(expression)).isEqualTo(CLASS_NAME);
        assertThat(stableExpressionToStringConverter.toString(expression)).isEqualTo(CLASS_NAME);
    }
}