package com.github.lizardev.xquery.saxon.coverage.trace;

import com.github.lizardev.xquery.saxon.support.trace.ExtensibleTraceExpression;
import net.sf.saxon.expr.Expression;
import net.sf.saxon.expr.parser.ExpressionVisitor;
import net.sf.saxon.trans.XPathException;
import org.apache.commons.lang3.StringUtils;

import static com.github.lizardev.xquery.saxon.coverage.trace.InstructionId.uniqueInstructionId;
import static org.apache.commons.lang3.StringUtils.isAlphanumeric;

@SuppressWarnings("serial")
public class CoverageExpression extends ExtensibleTraceExpression {

    private static final int OBJECTS_ADDRESS_NUMBER_OF_CHARACTERS = 8;
    private static final char AT_CHARACTER = '@';

    private final InstructionId instructionId = uniqueInstructionId();
    private final CoverageInstructionEventHandler eventHandler;

    public CoverageExpression(Expression child, CoverageInstructionEventHandler eventHandler) {
        super(child);
        this.eventHandler = eventHandler;
    }

    @Override
    public String toString() {
        String childToString = getChild().toString();
        return removeObjectsAddress(childToString);
    }

    private String removeObjectsAddress(String input) {
        int atCharacterIndex = input.indexOf(AT_CHARACTER);
        if (canHaveObjectsAddress(input, atCharacterIndex)) {
            int possibleObjectsAddressEndIndex = atCharacterIndex + OBJECTS_ADDRESS_NUMBER_OF_CHARACTERS;
            String possibleObjectsAddress = input.substring(atCharacterIndex + 1, possibleObjectsAddressEndIndex + 1);
            if (isAlphanumeric(possibleObjectsAddress)) {
                return input.replace(AT_CHARACTER + possibleObjectsAddress, StringUtils.EMPTY);
            }
        }
        return input;
    }

    private boolean canHaveObjectsAddress(String input, int atCharacterIndex) {
        return atCharacterIndex != -1 && lengthAfterAtCharacter(input, atCharacterIndex) >= OBJECTS_ADDRESS_NUMBER_OF_CHARACTERS;
    }

    private int lengthAfterAtCharacter(String input, int atCharacterIndex) {
        return input.substring(atCharacterIndex + 1, input.length()).length();
    }

    @Override
    public Expression simplify(ExpressionVisitor visitor) throws XPathException {
        Expression simplified = super.simplify(visitor);
        if (simplified != this) {
            eventHandler.handle(new CoverageInstructionSimplifiedEvent(instructionId));
        }
        return simplified;
    }

    public InstructionId getInstructionId() {
        return instructionId;
    }
}
