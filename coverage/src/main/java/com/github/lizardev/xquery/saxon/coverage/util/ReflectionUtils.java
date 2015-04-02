package com.github.lizardev.xquery.saxon.coverage.util;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;

public final class ReflectionUtils {

    private ReflectionUtils() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(Object object, String fieldName) {
        try {
            Field childField = FieldUtils.getField(object.getClass(), fieldName, true);
            return (T) FieldUtils.readField(childField, object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
