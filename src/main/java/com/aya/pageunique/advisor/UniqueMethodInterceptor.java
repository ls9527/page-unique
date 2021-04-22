package com.aya.pageunique.advisor;

import com.aya.pageunique.annotation.UniqueKey;
import com.aya.pageunique.invoke.Invoker;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class UniqueMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) {
        final Method method = methodInvocation.getMethod();
        String name = null;
        int index = -1;
        final Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            final UniqueKey param = parameter.getAnnotation(UniqueKey.class);
            if (param != null) {
                index = i;
                final Param annotation = parameter.getAnnotation(Param.class);
                if (annotation != null) {
                    name = annotation.value();
                }
                if (name == null) {
                    name = parameter.getName();
                }
                break;
            }
        }
        Invoker invoker = new Invoker(methodInvocation.getMethod(), methodInvocation.getThis(), methodInvocation.getArguments());
        return new UniqueListImpl<>(new DefaultUniqueIterator(index, name, invoker));
    }
}
