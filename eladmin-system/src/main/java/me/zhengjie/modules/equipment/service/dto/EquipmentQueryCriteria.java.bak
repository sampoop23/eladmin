package me.zhengjie.modules.equipment.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.Set;

import me.zhengjie.annotation.Query;

/**
* @author cp
* @date 2019-06-09
*/
@Data
public class EquipmentQueryCriteria{

    // 精确
    @Query
    private String gpsId;

    // 精确
    @Query
    private String equipmentNo;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String equipmentName;

    // 精确
    @Query
    private Integer equipmentType;

    // 精确
    @Query
    private Integer enabled;

    @Query(propName = "id", type = Query.Type.IN, joinName = "dept")
    private Set<Long> deptIds;

    private Long deptId;
}