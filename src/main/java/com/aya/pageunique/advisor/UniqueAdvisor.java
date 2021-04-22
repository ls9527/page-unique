package com.aya.pageunique.advisor;

import com.aya.pageunique.annotation.UniqueKey;
import org.aopalliance.aop.Advice;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Service
public class UniqueAdvisor implements PointcutAdvisor {

    @Override
    public Advice getAdvice() {
        return new UniqueMethodInterceptor();
    }

    @Override
    public boolean isPerInstance() {
        return true;
    }

    @Override
    public Pointcut getPointcut() {
        final MethodMatcher returnTypeMatcher = new MethodMatcher() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                boolean matchParameter = false;
                for (Annotation[] parameterAnnotation : method.getParameterAnnotations()) {
                    for (Annotation annotation : parameterAnnotation) {
                        if (UniqueKey.class.isAssignableFrom(annotation.annotationType())) {
                            matchParameter = true;
                            break;
                        }
                    }
                }
                return matchParameter;
            }

            @Override
            public boolean isRuntime() {
                return true;
            }

            @Override
            public boolean matches(Method method, Class<?> targetClass, Object... args) {
                throw new UnsupportedOperationException("returnTypeMatcher ");
            }
        };

        final StaticMethodMatcherPointcut staticMethodMatcherPointcut = new StaticMethodMatcherPointcut() {

            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                return returnTypeMatcher.matches(method, targetClass);
            }
        };
        return staticMethodMatcherPointcut;
    }
}
