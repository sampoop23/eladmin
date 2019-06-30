package me.zhengjie.modules.performance.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

/**
 * @author cp
 * @date 2019-06-16
 */
@Data
public class PerformanceHisDataTrashcanQueryCriteria {

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String gpsId;

    //
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @Query(propName = "dateTime", type = Query.Type.GREATER_THAN)
    private Date dateTimeSt;

    //
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @Query(propName = "dateTime", type = Query.Type.LESS_THAN)
    private Date dateTimeEd;

    // 精确
    @Query
    private Integer wtnG;

    // 精确
    @Query
    private Integer wtdG;

    // 精确
    @Query
    private Integer status;

    @Query(propName = "id", type = Query.Type.IN, joinName = "dept")
    private Set<Long> deptIds;

    private Long deptId;
}