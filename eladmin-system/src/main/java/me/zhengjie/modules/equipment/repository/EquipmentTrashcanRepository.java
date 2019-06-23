package me.zhengjie.modules.equipment.repository;

import me.zhengjie.modules.equipment.domain.EquipmentTrashcan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author cp
 * @date 2019-06-09
 */
public interface EquipmentTrashcanRepository extends JpaRepository<EquipmentTrashcan, Long>, JpaSpecificationExecutor {

    /**
     * findByGpsId
     *
     * @param gpsId
     * @return
     */
    EquipmentTrashcan findByGpsId(String gpsId);

    /**
     * findByEquipmentNo
     *
     * @param equipmentNo
     * @return
     */
    EquipmentTrashcan findByEquipmentNo(String equipmentNo);

//    /**
//     * findByEquipmentName
//     *
//     * @param equipmentName
//     * @return
//     */
//    EquipmentTrashcan findByEquipmentName(String equipmentName);
}