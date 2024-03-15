package com.yang;

import com.yang.constant.BitCaskConstant;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 内存中的key集合
 */
public class KeyDir {

    private volatile static KeyDir SINGLETON;

    private Map<String, String> keyIndexes;

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



}
