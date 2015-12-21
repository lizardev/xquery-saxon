package com.github.lizardev.xquery.saxon.support.util;

import com.google.common.base.Throwables;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;

public final class ReflectionUtils {

    private ReflectionUtils() {
    }

    public static void setFieldValue(Object object, String fieldName, Object fieldValue) {
        try {
            Field field = FieldUtils.getField(object.getClass(), fieldName, true);
            FieldUtils.writeField(field, object, fieldValue);
        } catch (IllegalAccessException e) {
            throw Throwables.propagate(e);
        }
    }
}