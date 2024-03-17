package com.yang;

import com.yang.constant.BitCaskConstant;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 内存中的key集合
 */
public class KeyDir {

    private volatile static KeyDir SINGLETON;

    private Map<String, ValueMeta> keyIndexes;

    public KeyDir() {

    }

    /**
     * 初始化内存索引
     */
    private void init() {
        // todo @lq
        File file = new File(BitCaskConstant.BIT_CASK_DATA_DIR);

    }


    /**
     * 获取单例KeyDir
     * @return KeyDir
     */
    public static KeyDir newInstance() {
        if (Objects.isNull(SINGLETON)) {
            synchronized (KeyDir.class) {
                if (Objects.isNull(SINGLETON)) {
                    SINGLETON = new KeyDir();
                    return SINGLETON;
                }
            }
        }
        return SINGLETON;
    }

    /**
     * 获取value元数据
     * @param key key值
     * @return value元数据
     */
    public ValueMeta getValueMeta(String key) {
        // todo @lq
        return null;
    }

    /**
     * 存放valueMeta
     * @param key key值
     * @param valueMeta value元数据
     * @return 老的value元数据
     */
    public ValueMeta putValueMeta(String key, ValueMeta valueMeta) {
        // todo @lq
        return null;
    }

    public List<String> keys() {
        // todo @lq
        return null;
    }

    /**
     * 是否包含某个key
     * @param key key值
     * @return 是否包含
     */
    public boolean containsKey(String key) {
        // todo @lq
        return false;
    }

    /**
     * 删除key
     * @param key key值
     */
    public void remove(String key) {
        // todo @lq
    }

    /**
     * key和value元数据是否相等
     * @param key key值
     * @param valueMeta 值元数据
     * @return key存在并且valueMeta相等
     */
    public boolean valueEquals(String key, ValueMeta valueMeta) {
        // todo @lq
        return false;
    }



}
