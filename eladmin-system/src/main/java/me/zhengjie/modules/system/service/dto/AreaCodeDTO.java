package me.zhengjie.modules.system.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;


/**
* @author cp
* @date 2019-06-12
*/
@Data
public class AreaCodeDTO implements Serializable {

    /**
     * 区划代码
     */
    // 处理精度丢失问题
    @JsonSerialize(using= ToStringSerializer.class)
    private Long code;

    /**
     * 名称
     */
    private String name;

    /**
     * 级别1-5,省市县镇村
     */
    private Integer level;

    /**
     * 父级区划代码
     */
    private Long pcode;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<AreaCodeDTO> children;
}