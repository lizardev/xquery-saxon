package com.github.lizardev.xquery.saxon.coverage.trace;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import net.sf.saxon.expr.Expression;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(JUnitParamsRunner.class)
public class StableExpressionToStringConverterTest {

    private Expression expression = mock(Expression.class);
    private StableExpressionToStringConverter stableExpressionToStringConverter = new StableExpressionToStringConverter();

    private Object[] hashCodeSuffixParameters() {
        return $(
                $("com.saxonica.functions.hof.CallableFunctionItem@6bee793f", "com.saxonica.functions.hof.CallableFunctionItem"),
                $("(aa,com.saxonica.functions.hof.UserFunctionItem@134ff8f8), Q{http://www.w3.org}entry(aa, com.saxonica.functions.hof.UserFunctionItem@619f2af)",
                        "(aa,com.saxonica.functions.hof.UserFunctionItem), Q{http://www.w3.org}entry(aa, com.saxonica.functions.hof.UserFunctionItem)"),
                $("substring-after($nextStep,'@')", "substring-after($nextStep,'@')"),
                $("com.saxonica.functions.hof.CallableFunctionItem", "com.saxonica.functions.hof.CallableFunctionItem")
        );
    }

    @Test
    @Parameters(method = "hashCodeSuffixParameters")
    public void shouldRemoveHashCodeSuffix(String beforeConversion, String afterConversion) {
        given(expression.toString()).willReturn(beforeConversion);

        assertThat(stableExpressionToStringConverter.toString(expression)).isEqualTo(afterConversion);
    }

    @Test
    public void shouldRemoveZeroLengthTextNodeAddedBySaxonAsWorkaround() {
        given(expression.toString()).willReturn("FixedElement(Block(Q{http://xquery.coverage/functions}fun1(), ValueOf(\"\"), Q{http://xquery.coverage/functions}fun2(), ValueOf(\"\"), Q{http://xquery.coverage/functions}fun3()))");

        assertThat(stableExpressionToStringConverter.toString(expression)).isEqualTo("FixedElement(Block(Q{http://xquery.coverage/functions}fun1(), Q{http://xquery.coverage/functions}fun2(), Q{http://xquery.coverage/functions}fun3()))");
    }

    @Test
    public void shouldReturnStoredValueWhenTheSameExpressionIsPassedSecondTimeInARow() {
        given(expression.toString())
                .willReturn("a")
                .willReturn("b");

        assertThat(stableExpressionToStringConverter.toString(expression)).isEqualTo("a");
        assertThat(stableExpressionToStringConverter.toString(expression)).isEqualTo("a");
    }
}