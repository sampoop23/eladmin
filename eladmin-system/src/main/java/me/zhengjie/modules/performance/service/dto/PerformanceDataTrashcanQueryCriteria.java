package me.zhengjie.modules.performance.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import me.zhengjie.annotation.Query;

/**
* @author cp
* @date 2019-06-16
*/
@Data
public class PerformanceDataTrashcanQueryCriteria{

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String gpsId;

    // 精确
    @Query
    private Timestamp dateTime;

    // 精确
    @Query
    private Integer wtnG;

    // 精确
    @Query
    private Integer wtdG;

    // 精确
    @Query
    private Integer status;
}