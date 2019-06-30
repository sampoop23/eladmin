package me.zhengjie.modules.performance.service;

import me.zhengjie.modules.performance.domain.PerformanceHisDataTrashcan;
import me.zhengjie.modules.performance.service.dto.PerformanceHisDataTrashcanDTO;
import me.zhengjie.modules.performance.service.dto.PerformanceHisDataTrashcanQueryCriteria;
import org.springframework.data.domain.Pageable;

/**
 * @author cp
 * @date 2019-06-16
 */
//@CacheConfig(cacheNames = "performanceHisDataTrashcan")
public interface PerformanceHisDataTrashcanService {

    /**
     * queryAll 分页
     *
     * @param criteria
     * @param pageable
     * @return
     */
//    @Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(PerformanceHisDataTrashcanQueryCriteria criteria, Pageable pageable);

    /**
     * queryAll 不分页
     *
     * @param criteria
     * @return
     */
//    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(PerformanceHisDataTrashcanQueryCriteria criteria);

    /**
     * findById
     *
     * @param id
     * @return
     */
//    @Cacheable(key = "#p0")
    PerformanceHisDataTrashcanDTO findById(Long id);

    /**
     * create
     *
     * @param resources
     * @return
     */
//    @CacheEvict(allEntries = true)
    PerformanceHisDataTrashcanDTO create(PerformanceHisDataTrashcan resources);

    /**
     * update
     *
     * @param resources
     */
//    @CacheEvict(allEntries = true)
    void update(PerformanceHisDataTrashcan resources);

    /**
     * delete
     *
     * @param id
     */
//    @CacheEvict(allEntries = true)
    void delete(Long id);
}