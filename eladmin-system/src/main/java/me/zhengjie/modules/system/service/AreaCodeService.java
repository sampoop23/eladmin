package me.zhengjie.modules.system.service;

import me.zhengjie.modules.system.domain.AreaCode;
import me.zhengjie.modules.system.service.dto.AreaCodeDTO;
import me.zhengjie.modules.system.service.dto.AreaCodeQueryCriteria;
import me.zhengjie.modules.system.service.dto.DeptDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author cp
 * @date -06-12
 */
@CacheConfig(cacheNames = "areaCode")
public interface AreaCodeService {

    /**
     * queryAll 分页
     *
     * @param criteria
     * @param pageable
     * @return
     */
    @Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(AreaCodeQueryCriteria criteria, Pageable pageable);

    /**
     * queryAll 不分页
     *
     * @param criteria
     * @return
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public List<AreaCodeDTO> queryAll(AreaCodeQueryCriteria criteria);

    /**
     * findById
     *
     * @param code
     * @return
     */
    @Cacheable(key = "#p0")
    AreaCodeDTO findById(Long code);

    /**
     * create
     *
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    AreaCodeDTO create(AreaCode resources);

    /**
     * update
     *
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(AreaCode resources);

    /**
     * delete
     *
     * @param code
     */
    @CacheEvict(allEntries = true)
    void delete(Long code);

    /**
     * buildTree
     *
     * @param areaCodeDTOS
     * @return
     */
    @Cacheable(keyGenerator = "keyGenerator")
    Object buildTree(List<AreaCodeDTO> areaCodeDTOS);
}