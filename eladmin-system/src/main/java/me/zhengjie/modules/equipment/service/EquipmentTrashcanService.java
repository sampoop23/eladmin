package me.zhengjie.modules.equipment.service;

import me.zhengjie.domain.QiniuConfig;
import me.zhengjie.domain.QiniuContent;
import me.zhengjie.modules.equipment.domain.EquipmentTrashcan;
import me.zhengjie.modules.equipment.service.dto.EquipmentTrashcanDTO;
import me.zhengjie.modules.equipment.service.dto.EquipmentTrashcanQueryCriteria;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author cp
 * @date 2019-06-09
 */
//@CacheConfig(cacheNames = "equipmentTrashcan")
public interface EquipmentTrashcanService {

    /**
     * queryAll 分页
     *
     * @param criteria
     * @param pageable
     * @return
     */
//    @Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(EquipmentTrashcanQueryCriteria criteria, Pageable pageable);

    /**
     * queryAll 不分页
     *
     * @param criteria
     * @return
     */
//    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(EquipmentTrashcanQueryCriteria criteria);

    /**
     * findById
     *
     * @param id
     * @return
     */
//    @Cacheable(key = "#p0")
    EquipmentTrashcanDTO findById(Long id);

    /**
     * create
     *
     * @param resources
     * @return
     */
//    @CacheEvict(allEntries = true)
    EquipmentTrashcanDTO create(EquipmentTrashcan resources);

    /**
     * update
     *
     * @param resources
     */
//    @CacheEvict(allEntries = true)
    void update(EquipmentTrashcan resources);

    /**
     * delete
     *
     * @param id
     */
//    @CacheEvict(allEntries = true)
    void delete(Long id);

    /**
     * findByGpsId
     *
     * @param gpsId
     * @return
     */
//    @Cacheable(key = "#p0")
    EquipmentTrashcanDTO findByGpsId(String gpsId);


    /**
     * 上传文件
     * @param file
     */
//    @CacheEvict(allEntries = true)
    boolean upload(MultipartFile file);
}