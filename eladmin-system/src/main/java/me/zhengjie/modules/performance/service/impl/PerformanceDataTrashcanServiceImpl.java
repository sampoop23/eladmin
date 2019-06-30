package me.zhengjie.modules.performance.service.impl;

import me.zhengjie.modules.performance.domain.PerformanceDataTrashcan;
import me.zhengjie.modules.performance.repository.PerformanceDataTrashcanRepository;
import me.zhengjie.modules.performance.service.PerformanceDataTrashcanService;
import me.zhengjie.modules.performance.service.dto.PerformanceDataTrashcanDTO;
import me.zhengjie.modules.performance.service.dto.PerformanceDataTrashcanQueryCriteria;
import me.zhengjie.modules.performance.service.mapper.PerformanceDataTrashcanMapper;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import me.zhengjie.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author cp
 * @date 2019-06-16
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PerformanceDataTrashcanServiceImpl implements PerformanceDataTrashcanService {

    @Autowired
    private PerformanceDataTrashcanRepository performanceDataTrashcanRepository;

    @Autowired
    private PerformanceDataTrashcanMapper performanceDataTrashcanMapper;

    @Override
    public Object queryAll(PerformanceDataTrashcanQueryCriteria criteria, Pageable pageable) {
        Page<PerformanceDataTrashcan> page = performanceDataTrashcanRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(performanceDataTrashcanMapper::toDto));
    }

    @Override
    public Object queryAll(PerformanceDataTrashcanQueryCriteria criteria) {
        return performanceDataTrashcanMapper.toDto(performanceDataTrashcanRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    public PerformanceDataTrashcanDTO findById(Long id) {
        Optional<PerformanceDataTrashcan> performanceDataTrashcan = performanceDataTrashcanRepository.findById(id);
        ValidationUtil.isNull(performanceDataTrashcan, "PerformanceDataTrashcan", "id", id);
        return performanceDataTrashcanMapper.toDto(performanceDataTrashcan.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PerformanceDataTrashcanDTO create(PerformanceDataTrashcan resources) {
        return performanceDataTrashcanMapper.toDto(performanceDataTrashcanRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(PerformanceDataTrashcan resources) {
        Optional<PerformanceDataTrashcan> optionalPerformanceDataTrashcan = performanceDataTrashcanRepository.findById(resources.getId());
        ValidationUtil.isNull(optionalPerformanceDataTrashcan, "PerformanceDataTrashcan", "id", resources.getId());

        PerformanceDataTrashcan performanceDataTrashcan = optionalPerformanceDataTrashcan.get();
        // 此处需自己修改
        resources.setId(performanceDataTrashcan.getId());
        performanceDataTrashcanRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        performanceDataTrashcanRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByGpsId(String gpsId) {
        performanceDataTrashcanRepository.deleteByGpsId(gpsId);
    }


}