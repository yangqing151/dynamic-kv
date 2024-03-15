package com.yang;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.function.BiConsumer;

public interface IStorage<K extends Serializable, V extends Serializable> {

    V get(K k);

    V put(K k, V v);

    V delete(K k);

    List<K> listKeys();

    /**
     * 遍历数据，执行action方法
     * @param action action执行方法
     */
    void fold(BiConsumer<? super K, ? super V> action);

    /**
     * 合并目录下所有文件
     * @param file 目录
     */
    void merge(File file);

    /**
     * 打开目录
     * @param file 文件目录
     * @param openOptions 打开选项
     */
    void open(File file, OpenOptions openOptions);

    /**
     * 将数据同步到磁盘中
     */
    void sync();

    /**
     * 关闭存储
     */
    void close();



}
