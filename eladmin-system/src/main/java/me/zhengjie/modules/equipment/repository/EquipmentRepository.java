package me.zhengjie.modules.equipment.repository;

import me.zhengjie.modules.equipment.domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author cp
 * @date 2019-06-09
 */
public interface EquipmentRepository extends JpaRepository<Equipment, Long>, JpaSpecificationExecutor {

    /**
     * findByGpsId
     *
     * @param gpsId
     * @return
     */
    Equipment findByGpsId(String gpsId);

    /**
     * findByEquipmentNo
     *
     * @param equipmentNo
     * @return
     */
    Equipment findByEquipmentNo(String equipmentNo);

    /**
     * findByEquipmentName
     *
     * @param equipmentName
     * @return
     */
    Equipment findByEquipmentName(String equipmentName);
}