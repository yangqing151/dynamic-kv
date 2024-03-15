package com.yang;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 值的元数据
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ValueMeta {

    /**
     * 文件ID
     * 固定长度（秒级时间戳） todo @lq 待确定更好的形式
     */
    private String fileId;

    /**
     * 值的大小
     */
    private Integer valueSize;

    /**
     * 值的偏移量位置
     */
    private Integer valuePosition;

    /**
     * 存储的毫秒时间戳
     */
    private Long timestamp;


}
