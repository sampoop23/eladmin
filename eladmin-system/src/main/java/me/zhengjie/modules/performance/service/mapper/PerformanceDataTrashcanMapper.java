package me.zhengjie.modules.performance.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.performance.domain.PerformanceDataTrashcan;
import me.zhengjie.modules.performance.service.dto.PerformanceDataTrashcanDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author cp
 * @date 2019-06-16
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PerformanceDataTrashcanMapper extends EntityMapper<PerformanceDataTrashcanDTO, PerformanceDataTrashcan> {

}