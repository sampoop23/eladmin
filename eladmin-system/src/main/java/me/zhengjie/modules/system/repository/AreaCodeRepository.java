package me.zhengjie.modules.system.repository;

import me.zhengjie.modules.system.domain.AreaCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author cp
 * @date 2019-06-12
 */
public interface AreaCodeRepository extends JpaRepository<AreaCode, Long>, JpaSpecificationExecutor {

    @Query(value = "select name from area_code_2019 where code = ?1", nativeQuery = true)
    String findNameByCode(Long id);
}