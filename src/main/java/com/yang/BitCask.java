package com.yang;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.function.BiConsumer;

public class BitCask<K extends Serializable, V extends Serializable> implements IStorage<K, V>{

    private KeyDir keyDir;

    public BitCask() {
        this.keyDir = KeyDir.newInstance();
    }



    @Override
    public V get(K k) {
        return null;
    }

    @Override
    public V put(K k, V v) {
        return null;
    }

    @Override
    public V delete(K k) {
        return null;
    }

    @Override
    public List<K> listKeys() {
        return null;
    }

    @Override
    public void fold(BiConsumer<? super K, ? super V> action) {

    }

    @Override
    public void merge(File file) {

    }

    @Override
    public void open(File file, OpenOptions openOptions) {

    }

    @Override
    public void sync() {

    }

    @Override
    public void close() {

    }
}
