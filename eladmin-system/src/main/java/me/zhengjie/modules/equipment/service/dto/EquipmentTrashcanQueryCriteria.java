package me.zhengjie.modules.equipment.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;

import java.util.Set;

/**
 * @author cp
 * @date 2019-06-09
 */
@Data
public class EquipmentTrashcanQueryCriteria {

    // 精确
    @Query
    private String gpsId;

    // 精确
    @Query
    private String equipmentNo;

//    // 模糊
//    @Query(type = Query.Type.INNER_LIKE)
//    private String equipmentName;

    // 精确
    @Query
    private Integer trashcanType;

    // 精确
    @Query
    private Integer garbageType;

    @Query(propName = "id", type = Query.Type.IN, joinName = "dept")
    private Set<Long> deptIds;

    private Long deptId;
}