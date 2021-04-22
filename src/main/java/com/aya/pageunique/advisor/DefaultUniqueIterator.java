package com.aya.pageunique.advisor;

import com.aya.pageunique.exception.UniquePageException;
import com.aya.pageunique.invoke.Invoker;
import com.aya.pageunique.iterator.AbstractUniqueIterator;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

class DefaultUniqueIterator<T> extends AbstractUniqueIterator<T, Object> {
    private final int index;
    private final String name;
    private final Invoker invoker;

    public DefaultUniqueIterator(int index, String name, Invoker invoker) {
        this.index = index;
        this.name = name;
        this.invoker = invoker;
    }

    @Override
    protected List<T> selectData(Object id, int size) {
        try {
            final Object[] arguments = invoker.getArguments();
            arguments[index] = id;
            return (List<T>) invoker.invoke(arguments);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new UniquePageException("执行方法失败, name:" + name + ",index:" + index, e);
        }
    }

    @Override
    protected Object getId(Object data) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(data);
        return beanWrapper.getPropertyValue(name);
    }
}
