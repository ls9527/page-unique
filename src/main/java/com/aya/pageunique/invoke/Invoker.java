package com.aya.pageunique.invoke;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Invoker {

    private Method method;
    private Object bean;
    private Object[] arguments;

    public Invoker(Method method, Object bean, Object[] arguments) {
        this.method = method;
        this.bean = bean;
        this.arguments = arguments;
    }

    public Object invoke() throws InvocationTargetException, IllegalAccessException {
        return invoke(this.arguments);
    }

    public Object invoke(Object[] arguments) throws InvocationTargetException, IllegalAccessException {
        return this.method.invoke(bean, arguments);
    }

    public Object[] getArguments() {
        return arguments;
    }
}
