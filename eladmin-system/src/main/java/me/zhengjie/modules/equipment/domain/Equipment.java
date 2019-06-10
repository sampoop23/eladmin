package me.zhengjie.modules.equipment.domain;

import lombok.Data;
import me.zhengjie.modules.system.domain.Dept;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author cp
 * @date 2019-06-09
 */
@Entity
@Data
@Table(name = "equipment")
public class Equipment implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * gps号
     */
    @Column(name = "gps_id")
    private String gpsId;

    /**
     * 设备号
     */
    @Column(name = "equipment_no")
    private String equipmentNo;

    /**
     * 设备名
     */
    @Column(name = "equipment_name")
    private String equipmentName;

    /**
     * 设备类型
     */
    @Column(name = "equipment_type")
    private Integer equipmentType;

    /**
     * 状态：1启用、0禁用
     */
    @Column(name = "enabled")
    private Integer enabled;

    /**
     * 创建日期
     */
    @Column(name = "create_time")
    private Timestamp createTime;

//    @Column(name = "dept_id")
//    private Long deptId;

    @OneToOne
    @JoinColumn(name = "dept_id")
    private Dept dept;
}