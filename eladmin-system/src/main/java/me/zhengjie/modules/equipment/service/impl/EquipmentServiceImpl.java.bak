package me.zhengjie.modules.equipment.service.impl;

import me.zhengjie.exception.EntityExistException;
import me.zhengjie.modules.equipment.domain.Equipment;
import me.zhengjie.modules.equipment.repository.EquipmentRepository;
import me.zhengjie.modules.equipment.service.EquipmentService;
import me.zhengjie.modules.equipment.service.dto.EquipmentDTO;
import me.zhengjie.modules.equipment.service.dto.EquipmentQueryCriteria;
import me.zhengjie.modules.equipment.service.mapper.EquipmentMapper;
import me.zhengjie.modules.system.domain.User;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import me.zhengjie.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author cp
 * @date 2019-06-09
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public Object queryAll(EquipmentQueryCriteria criteria, Pageable pageable) {
        Page<Equipment> page = equipmentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(equipmentMapper::toDto));
    }

    @Override
    public Object queryAll(EquipmentQueryCriteria criteria) {
        return equipmentMapper.toDto(equipmentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    public EquipmentDTO findById(Long id) {
        Optional<Equipment> equipment = equipmentRepository.findById(id);
        ValidationUtil.isNull(equipment, "Equipment", "id", id);
        return equipmentMapper.toDto(equipment.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EquipmentDTO create(Equipment resources) {
        if (equipmentRepository.findByGpsId(resources.getGpsId()) != null) {
            throw new EntityExistException(Equipment.class, "gpsId", resources.getGpsId());
        }
        if (equipmentRepository.findByEquipmentNo(resources.getEquipmentNo()) != null) {
            throw new EntityExistException(Equipment.class, "equipmentNo", resources.getEquipmentNo());
        }
        if (equipmentRepository.findByEquipmentName(resources.getEquipmentName()) != null) {
            throw new EntityExistException(Equipment.class, "equipmentName", resources.getEquipmentName());
        }
        return equipmentMapper.toDto(equipmentRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Equipment resources) {
        Optional<Equipment> optionalEquipment = equipmentRepository.findById(resources.getId());
        ValidationUtil.isNull(optionalEquipment, "Equipment", "id", resources.getId());

        Equipment equipment = optionalEquipment.get();


        Equipment equipment1 = equipmentRepository.findByGpsId(resources.getGpsId());
        Equipment equipment2 = equipmentRepository.findByEquipmentNo(resources.getEquipmentNo());
        Equipment equipment3 = equipmentRepository.findByEquipmentName(resources.getEquipmentName());

        if (equipment1 != null && !equipment.getId().equals(equipment1.getId())) {
            throw new EntityExistException(User.class, "gpsId", resources.getGpsId());
        }

        if (equipment2 != null && !equipment.getId().equals(equipment2.getId())) {
            throw new EntityExistException(User.class, "equipmentNo", resources.getEquipmentNo());
        }

        if (equipment3 != null && !equipment.getId().equals(equipment3.getId())) {
            throw new EntityExistException(User.class, "equipmentName", resources.getEquipmentName());
        }

        // 此处需自己修改
//        if (!resources.getDept().equals(equipment.getDept())) {
//            String key = "role::loadPermissionByUser:" + user.getUsername();
//            redisService.delete(key);
//            key = "role::findByUsers_Id:" + user.getId();
//            redisService.delete(key);
//        }

        resources.setId(equipment.getId());
        equipmentRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        equipmentRepository.deleteById(id);
    }
}