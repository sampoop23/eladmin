package me.zhengjie.modules.system.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;

/**
* @author cp
* @date 2019-06-12
*/
@Data
public class AreaCodeQueryCriteria{

    // 精确
    @Query
    private Long code;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    // 精确
    @Query
    private Integer level;

    // 精确
    @Query
    private Long pcode;
}