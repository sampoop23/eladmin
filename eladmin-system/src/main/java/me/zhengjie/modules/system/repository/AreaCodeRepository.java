package me.zhengjie.modules.system.repository;

import me.zhengjie.modules.system.domain.AreaCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author cp
 * @date 2019-06-12
 */
public interface AreaCodeRepository extends JpaRepository<AreaCode, Long>, JpaSpecificationExecutor {
}