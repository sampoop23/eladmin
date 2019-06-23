package me.zhengjie.modules.equipment.service.impl;

import me.zhengjie.exception.EntityExistException;
import me.zhengjie.modules.equipment.domain.EquipmentTrashcan;
import me.zhengjie.modules.equipment.repository.EquipmentTrashcanRepository;
import me.zhengjie.modules.equipment.service.EquipmentTrashcanService;
import me.zhengjie.modules.equipment.service.dto.EquipmentTrashcanDTO;
import me.zhengjie.modules.equipment.service.dto.EquipmentTrashcanQueryCriteria;
import me.zhengjie.modules.equipment.service.mapper.EquipmentTrashcanMapper;
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
public class EquipmentTrashcanServiceImpl implements EquipmentTrashcanService {

    @Autowired
    private EquipmentTrashcanRepository equipmentTrashcanRepository;

    @Autowired
    private EquipmentTrashcanMapper equipmentTrashcanMapper;

    @Override
    public Object queryAll(EquipmentTrashcanQueryCriteria criteria, Pageable pageable) {
        Page<EquipmentTrashcan> page = equipmentTrashcanRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(equipmentTrashcanMapper::toDto));
    }

    @Override
    public Object queryAll(EquipmentTrashcanQueryCriteria criteria) {
        return equipmentTrashcanMapper.toDto(equipmentTrashcanRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    public EquipmentTrashcanDTO findById(Long id) {
        Optional<EquipmentTrashcan> equipment = equipmentTrashcanRepository.findById(id);
        ValidationUtil.isNull(equipment, "EquipmentTrashcan", "id", id);
        return equipmentTrashcanMapper.toDto(equipment.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EquipmentTrashcanDTO create(EquipmentTrashcan resources) {
        if (equipmentTrashcanRepository.findByGpsId(resources.getGpsId()) != null) {
            throw new EntityExistException(EquipmentTrashcan.class, "gpsId", resources.getGpsId());
        }
        if (equipmentTrashcanRepository.findByEquipmentNo(resources.getEquipmentNo()) != null) {
            throw new EntityExistException(EquipmentTrashcan.class, "equipmentNo", resources.getEquipmentNo());
        }
        if (equipmentTrashcanRepository.findByEquipmentName(resources.getEquipmentName()) != null) {
            throw new EntityExistException(EquipmentTrashcan.class, "equipmentName", resources.getEquipmentName());
        }

        return equipmentTrashcanMapper.toDto(equipmentTrashcanRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(EquipmentTrashcan resources) {
        Optional<EquipmentTrashcan> optionalEquipmentTrashcan = equipmentTrashcanRepository.findById(resources.getId());
        ValidationUtil.isNull(optionalEquipmentTrashcan, "EquipmentTrashcan", "id", resources.getId());

        EquipmentTrashcan equipmentTrashcan = optionalEquipmentTrashcan.get();


        EquipmentTrashcan equipmentTrashcan1 = equipmentTrashcanRepository.findByGpsId(resources.getGpsId());
        EquipmentTrashcan equipmentTrashcan2 = equipmentTrashcanRepository.findByEquipmentNo(resources.getEquipmentNo());
        EquipmentTrashcan equipmentTrashcan3 = equipmentTrashcanRepository.findByEquipmentName(resources.getEquipmentName());

        if (equipmentTrashcan1 != null && !equipmentTrashcan.getId().equals(equipmentTrashcan1.getId())) {
            throw new EntityExistException(EquipmentTrashcan.class, "gpsId", resources.getGpsId());
        }

        if (equipmentTrashcan2 != null && !equipmentTrashcan.getId().equals(equipmentTrashcan2.getId())) {
            throw new EntityExistException(EquipmentTrashcan.class, "equipmentNo", resources.getEquipmentNo());
        }

        if (equipmentTrashcan3 != null && !equipmentTrashcan.getId().equals(equipmentTrashcan3.getId())) {
            throw new EntityExistException(EquipmentTrashcan.class, "equipmentName", resources.getEquipmentName());
        }

        // 此处需自己修改
//        if (!resources.getDept().equals(equipment.getDept())) {
//            String key = "role::loadPermissionByUser:" + user.getUsername();
//            redisService.delete(key);
//            key = "role::findByUsers_Id:" + user.getId();
//            redisService.delete(key);
//        }

//        //
        equipmentTrashcan.setGpsId(resources.getGpsId());
        equipmentTrashcan.setEquipmentNo(resources.getEquipmentNo());
        equipmentTrashcan.setEquipmentName(resources.getEquipmentName());
        equipmentTrashcan.setAddressProv(resources.getAddressProv());
        equipmentTrashcan.setAddressCity(resources.getAddressCity());
        equipmentTrashcan.setAddressRegion(resources.getAddressRegion());
        equipmentTrashcan.setAddressStreet(resources.getAddressStreet());
        equipmentTrashcan.setAddressRoom(resources.getAddressRoom());
        equipmentTrashcan.setDept(resources.getDept());
        //
        equipmentTrashcanRepository.save(equipmentTrashcan);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {

        Optional<EquipmentTrashcan> optionalEquipmentTrashcan = equipmentTrashcanRepository.findById(id);
        ValidationUtil.isNull(optionalEquipmentTrashcan, "EquipmentTrashcan", "id", id);
        EquipmentTrashcan equipmentTrashcan = optionalEquipmentTrashcan.get();
        Long eqId = equipmentTrashcan.getId();
        //
        equipmentTrashcanRepository.deleteById(id);
    }

    @Override
    public EquipmentTrashcanDTO findByGpsId(String gpsId) {
        EquipmentTrashcan equipment = equipmentTrashcanRepository.findByGpsId(gpsId);
        ValidationUtil.isNull(equipment, "EquipmentTrashcan", "gpsId", gpsId);
        return equipmentTrashcanMapper.toDto(equipment);
    }
}