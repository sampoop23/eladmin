package me.zhengjie.modules.equipment.domain;

import lombok.Data;
import me.zhengjie.modules.system.domain.Dept;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author cp
 * @date 2019-06-09
 */
@Entity
@Data
@Table(name = "equipment_trashcan")
public class EquipmentTrashcan implements Serializable {

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

//    /**
//     * 设备名
//     */
//    @Column(name = "equipment_name")
//    private String equipmentName;

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
     * 创建日期
     */
    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Dept dept;

}