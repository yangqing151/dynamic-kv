package com.yang;

import lombok.Data;

import java.util.Objects;

@Data
public class LogEntry {

    /**
     * 固定32位长度
     */
    private String crc32;

    private Long timestamp;

    private Integer keySize;

    private Integer valueSize;

    private String key;

    private String value;

    /**
     * 获取LogEntry的长度
     * @return 32位的crc + 8位的时间戳 + 4位的keySize + 4位的valueSize  + keySize的key + valueSize的value
     */
    public Integer getSize() {
        int kSize = Objects.nonNull(keySize) ? keySize : 0;
        int vSize = Objects.nonNull(valueSize) ? valueSize : 0;
        return 32 + 8 + 4 + 4 + kSize + vSize;
    }

    /**
     * 获取value的位置
     * @param offset
     * @return
     */
    public Integer getValuePosition(Integer offset) {
        int kSize = Objects.nonNull(keySize) ? keySize : 0;
        return offset + 32 + 8 + 4 + 4 + kSize;
    }



}
