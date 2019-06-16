package me.zhengjie.modules.equipment.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.equipment.domain.EquipmentTrashcan;
import me.zhengjie.modules.equipment.service.dto.EquipmentTrashcanDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author cp
 * @date 2019-06-09
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EquipmentTrashcanMapper extends EntityMapper<EquipmentTrashcanDTO, EquipmentTrashcan> {

}