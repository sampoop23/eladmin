package me.zhengjie.modules.equipment.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.equipment.domain.Equipment;
import me.zhengjie.modules.equipment.service.dto.EquipmentDTO;
import me.zhengjie.modules.system.service.mapper.DeptMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author cp
 * @date 2019-06-09
 */
@Mapper(componentModel = "spring", uses = {DeptMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EquipmentMapper extends EntityMapper<EquipmentDTO, Equipment> {

}