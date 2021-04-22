package com.aya.pageunique.advisor;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;

class UniqueListImpl<T> extends AbstractList<T> implements List<T> {

    private Iterator<T> iterator;

    public UniqueListImpl(Iterator<T> iterator) {
        this.iterator = iterator;
    }

    @Override
    public Iterator<T> iterator() {
        return iterator;
    }


    @Override
    public T get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }
}
