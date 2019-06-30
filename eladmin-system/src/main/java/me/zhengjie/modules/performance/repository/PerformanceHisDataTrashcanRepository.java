package me.zhengjie.modules.performance.repository;

import me.zhengjie.modules.performance.domain.PerformanceHisDataTrashcan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author cp
 * @date 2019-06-16
 */
public interface PerformanceHisDataTrashcanRepository extends JpaRepository<PerformanceHisDataTrashcan, Long>, JpaSpecificationExecutor {
}