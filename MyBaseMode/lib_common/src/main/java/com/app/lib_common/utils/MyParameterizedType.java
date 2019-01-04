package com.app.lib_common.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 实现ParameterizedType接口用于生成Type
 *
 * @author lt
 * @time 2018/12/11 11:54
 **/
public class MyParameterizedType implements ParameterizedType {
    private final Class raw;
    private final Type[] args;

    public MyParameterizedType(Class raw, Type[] args) {
        this.raw = raw;
        this.args = args != null ? args : new Type[0];
    }

    @Override
    public Type[] getActualTypeArguments() {
        return args;
    }

    @Override
    public Type getRawType() {
        return raw;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}