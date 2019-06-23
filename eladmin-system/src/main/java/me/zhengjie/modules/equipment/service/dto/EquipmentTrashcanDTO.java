package me.zhengjie.modules.equipment.service.dto;

import lombok.Data;
import me.zhengjie.modules.system.service.dto.DeptDTO;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * @author cp
 * @date 2019-06-09
 */
@Data
public class EquipmentTrashcanDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * gps号
     */
    private String gpsId;

    /**
     * 设备号
     */
    private String equipmentNo;

//    /**
//     * 设备名
//     */
//    private String equipmentName;

    /**
     * 垃圾桶类型
     */
    private String trashcanType;

    /**
     * 垃圾类型
     */
    private String garbageType;

    /**
     * 地址
     */
    private String addressProv;
    private String addressCity;
    private String addressRegion;
    private String addressStreet;
    private String addressRoom;

    /**
     * 创建日期
     */
    private Timestamp createTime;

    private DeptDTO dept;

    private Long deptId;

}