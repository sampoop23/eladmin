package me.zhengjie.modules.equipment.service;

import me.zhengjie.modules.equipment.domain.Equipment;
import me.zhengjie.modules.equipment.service.dto.EquipmentDTO;
import me.zhengjie.modules.equipment.service.dto.EquipmentQueryCriteria;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
* @author cp
* @date 2019-06-09
*/
@CacheConfig(cacheNames = "equipment")
public interface EquipmentService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    @Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(EquipmentQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(EquipmentQueryCriteria criteria);

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    EquipmentDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    EquipmentDTO create(Equipment resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Equipment resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}