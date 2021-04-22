package com.aya.pageunique.iterator;


import java.util.Iterator;
import java.util.List;

/**
 * @Description 唯一建迭代器
 * @Author ls4137
 * @Date 20210422
 **/
public abstract class AbstractUniqueIterator<T, ID> implements Iterator<T> {

    protected int size = 1000;
    /**
     * id 迭代器的id
     */
    protected ID id;
    /**
     * 查询内容的一组缓存
     */
    private List<T> content;

    private int index = -1;

    /**
     * 查询数据的方法
     *
     * @param id
     * @param size
     * @return
     */
    protected abstract List<T> selectData(ID id, int size);

    /**
     * 获得数据的 ID
     *
     * @param data
     * @return
     */
    protected abstract ID getId(T data);

    @Override
    public boolean hasNext() {
        fetchData();
        return !(content == null || content.isEmpty());
    }

    @Override
    public T next() {
        if (content == null) {
            throw new IllegalStateException("id迭代器 没有更多的内容了");
        }
        fetchData();
        final T t = content.get(index);
        index++;
        if (index == content.size()) {
            content = null;
        }
        return t;
    }

    private void fetchData() {
        if (content == null) {
            List<T> data = selectData(id, size);
            if (data.size() > 0) {
                content = data;
                id = getId(content.get(content.size() - 1));
                index = 0;
            }
        }
    }

    @Override
    public void remove() {
        throw new IllegalStateException("不可以在遍历的时候删除内容");
    }
}
