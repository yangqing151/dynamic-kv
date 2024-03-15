package com.yang;

import org.apache.commons.collections4.CollectionUtils;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

public class BitCask implements IStorage {

    private KeyDir keyDir;

    private BitCaskManager bitCaskManager;

    public BitCask() {
        this.keyDir = KeyDir.newInstance();
        this.bitCaskManager = new BitCaskManager();
    }


    @Override
    public String get(String key) {
        if (Objects.isNull(key)) {
            throw new IllegalArgumentException("key can not be null");
        }

        // 获取内存中key对应的值元数据
        ValueMeta valueMeta = keyDir.getValueMeta(key);
        if (Objects.isNull(valueMeta)) {
            return null;
        }

        // 从磁盘中读取数据
        return bitCaskManager.read(valueMeta);
    }

    @Override
    public void put(String key, String value) {
        if (Objects.isNull(key)) {
            throw new IllegalArgumentException("key can not be null");
        }
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("value can not be null");
        }
        
        // 更新磁盘数据
        ValueMeta valueMeta = bitCaskManager.write(key, value);

        // 更新内存值元数据
        keyDir.putValueMeta(key, valueMeta);

    }

    @Override
    public void delete(String key) {
    }

    @Override
    public List<String> listKeys() {
        return null;
    }

    @Override
    public void fold(BiConsumer<String, String> action) {
        List<String> keys = listKeys();
        if (CollectionUtils.isNotEmpty(keys)) {
            keys.forEach(key -> {
                String value = get(key);
                action.accept(key, value);
            });
        }
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
