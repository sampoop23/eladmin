package me.zhengjie.modules.performance.repository;

import me.zhengjie.modules.equipment.domain.EquipmentTrashcan;
import me.zhengjie.modules.performance.domain.PerformanceDataTrashcan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author cp
 * @date 2019-06-16
 */
public interface PerformanceDataTrashcanRepository extends JpaRepository<PerformanceDataTrashcan, Long>, JpaSpecificationExecutor {


    /**
     * findByGpsId
     *
     * @param gpsId
     * @return
     */
    PerformanceDataTrashcan findByGpsId(String gpsId);

    /**
     * deleteByGpsId
     *
     * @param gpsId
     * @return
     */
    int deleteByGpsId(String gpsId);


}