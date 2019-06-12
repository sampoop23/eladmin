package me.zhengjie.modules.system.service.mapper;


import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.system.domain.AreaCode;
import me.zhengjie.modules.system.service.dto.AreaCodeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author cp
 * @date 2019-06-12
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AreaCodeMapper extends EntityMapper<AreaCodeDTO, AreaCode> {

}