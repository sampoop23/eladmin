package me.zhengjie.modules.performance.service.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.io.Serializable;


/**
 * @author cp
 * @date 2019-06-16
 */
@Data
public class PerformanceDataTrashcanDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * gps编号
     */
    private String gpsId;

    /**
     * 日期时间
     */
    private Timestamp dateTime;

    /**
     * 当前采样时刻的重量
     */
    private Integer wtnG;

    /**
     * 某次开盖垃圾桶倾倒的重量
     */
    private Integer wtdG;

    /**
     * 状态码 0：系统正常。10：垃圾桶装满了。11：垃圾桶已经清空。20：电池需要更换提醒。30：通信异常
     */
    private Integer status;

    /**
     * 错误信息
     */
    private String errInfo;
}