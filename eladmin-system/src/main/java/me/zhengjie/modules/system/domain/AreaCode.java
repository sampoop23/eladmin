package me.zhengjie.modules.system.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author cp
 * @date 2019-06-12
 */
@Entity
@Data
@Table(name = "area_code_2019")
public class AreaCode implements Serializable {

    /**
     * 区划代码
     */
    @Id
    @Column(name = "code")
    private Long code;

    /**
     * 名称
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 级别1-5,省市县镇村
     */
    @Column(name = "level", nullable = false)
    private Integer level;

    /**
     * 父级区划代码
     */
    @Column(name = "pcode")
    private Long pcode;
}