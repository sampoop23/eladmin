package me.zhengjie.modules.performance.domain;

import lombok.Data;
import me.zhengjie.modules.system.domain.Dept;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author cp
 * @date 2019-06-16
 */
@Entity
@Data
@Table(name = "performance_data_trashcan")
public class PerformanceDataTrashcan implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * gps编号
     */
    @Column(name = "gps_id", nullable = false)
    private String gpsId;

    /**
     * 日期时间
     */
    @CreationTimestamp
    @Column(name = "date_time")
    private Timestamp dateTime;

    /**
     * 当前采样时刻的重量
     */
    @Column(name = "wtn_g")
    private Integer wtnG;

    /**
     * 某次开盖垃圾桶倾倒的重量
     */
    @Column(name = "wtd_g")
    private Integer wtdG;

    /**
     * 状态码 0：系统正常。10：垃圾桶装满了。11：垃圾桶已经清空。20：电池需要更换提醒。30：通信异常
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 错误信息
     */
    @Column(name = "err_info")
    private String errInfo;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Dept dept;

    /**
     * 地址
     */
    @Column(name = "address_prov")
    private String addressProv;
    @Column(name = "address_city")
    private String addressCity;
    @Column(name = "address_region")
    private String addressRegion;
    @Column(name = "address_street")
    private String addressStreet;
    @Column(name = "address_room")
    private String addressRoom;

    /**
     * 垃圾桶类型
     */
    @Column(name = "trashcan_type")
    private String trashcanType;

    /**
     * 垃圾类型
     */
    @Column(name = "garbage_type")
    private String garbageType;
}