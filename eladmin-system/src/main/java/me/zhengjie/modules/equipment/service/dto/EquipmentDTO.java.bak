package me.zhengjie.modules.equipment.service.dto;

import lombok.Data;
import me.zhengjie.modules.system.service.dto.DeptDTO;

import java.sql.Timestamp;
import java.io.Serializable;


/**
* @author cp
* @date 2019-06-09
*/
@Data
public class EquipmentDTO implements Serializable {

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

    /**
     * 设备名
     */
    private String equipmentName;

    /**
     * 设备类型
     */
    private Integer equipmentType;

    /**
     * 状态：1启用、0禁用
     */
    private Integer enabled;

    /**
     * 创建日期
     */
    private Timestamp createTime;

    private DeptDTO dept;

    private Long deptId;
}