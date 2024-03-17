package com.yang;

public class BitCaskManager {
    // todo @lq 读写锁，保证只有一个进程去写数据


    /**
     * 读取值
     * @param valueMeta value元数据
     * @return value值
     */
    public String read(ValueMeta valueMeta) {
        // todo @lq
        return null;

    }

    /**
     * 写值
     * @param key key值
     * @param value value值
     * @return value元数据
     */
    public ValueMeta write(String key, String value) {
        // todo @lq
        return null;

    }

    /**
     * 写入删除的key
     * @param key 删除的key
     * @return value元数据，时间戳为0
     */
    public ValueMeta writeDeleteKey(String key) {
        // todo @lq
        return null;
    }
}
