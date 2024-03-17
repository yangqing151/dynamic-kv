package com.yang;

import com.yang.constant.BitCaskConstant;
import org.apache.commons.collections4.CollectionUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class BitCask implements IStorage {

    /**
     * 文件比较器，文件名称倒序排序
     */
    private static final Comparator<File> FILE_NAME_DESC_COMPARATOR = (x, y) -> Integer.compare(0, x.getName().compareTo(y.getName()));


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
        if (Objects.isNull(key)) {
            throw new IllegalArgumentException("key can not be null");
        }

        // 写入磁盘空数据
        // 这边写入，使得merge时要将之前写入的值删除
        bitCaskManager.writeDeleteKey(key);

        // 删除内存值元数据
        keyDir.remove(key);
    }

    @Override
    public List<String> listKeys() {

        return keyDir.keys();
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
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("file must be directory");
        }

        // 只找data文件
        File[] files = file.listFiles((dir, name) -> name.contains(BitCaskConstant.BIT_CASK_DATA_PREFIX));

        // 没有文件或者文件数不超过2，不用处理
        if (Objects.isNull(files) || files.length <= 2) {
            return;
        }

        // 获取所有old文件，排除最新的active文件
        List<File> oldSortedFiles = Arrays.stream(files)
                .sorted(FILE_NAME_DESC_COMPARATOR)
                .limit(files.length - 1)
                .collect(Collectors.toList());

        for (File oldFile : oldSortedFiles) {
            // 遍历file所有key，处理
            int offset = 0;
            LogEntry logEntry = null;
            do {
                logEntry = FileManager.read(file, offset);
                if (Objects.isNull(logEntry)) {
                    break;
                }

                // keyDir中存在该key
                ValueMeta valueMeta = ValueMeta.builder()
                        .fileId(oldFile.getName())
                        .valueSize(logEntry.getValueSize())
                        .valuePosition(logEntry.getValuePosition(offset))
                        .timestamp(logEntry.getTimestamp())
                        .build();
                if (keyDir.valueEquals(logEntry.getKey(),  valueMeta)) {
                    // 写入merge文件


                    // 写入hint文件，如果此时存在hint文件如何处理
                    // todo @lq 巩固file使用
                }

                offset += logEntry.getSize();
            } while (true);

        }





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
