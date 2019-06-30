package me.zhengjie.modules.performance.service.impl;

import me.zhengjie.modules.performance.domain.PerformanceHisDataTrashcan;
import me.zhengjie.modules.performance.repository.PerformanceHisDataTrashcanRepository;
import me.zhengjie.modules.performance.service.PerformanceHisDataTrashcanService;
import me.zhengjie.modules.performance.service.dto.PerformanceHisDataTrashcanDTO;
import me.zhengjie.modules.performance.service.dto.PerformanceHisDataTrashcanQueryCriteria;
import me.zhengjie.modules.performance.service.mapper.PerformanceHisDataTrashcanMapper;
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
public class PerformanceHisDataTrashcanServiceImpl implements PerformanceHisDataTrashcanService {

    @Autowired
    private PerformanceHisDataTrashcanRepository performanceHisDataTrashcanRepository;

    @Autowired
    private PerformanceHisDataTrashcanMapper performanceHisDataTrashcanMapper;

    @Override
    public Object queryAll(PerformanceHisDataTrashcanQueryCriteria criteria, Pageable pageable) {
        Page<PerformanceHisDataTrashcan> page = performanceHisDataTrashcanRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(performanceHisDataTrashcanMapper::toDto));
    }

    @Override
    public Object queryAll(PerformanceHisDataTrashcanQueryCriteria criteria) {
        return performanceHisDataTrashcanMapper.toDto(performanceHisDataTrashcanRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    public PerformanceHisDataTrashcanDTO findById(Long id) {
        Optional<PerformanceHisDataTrashcan> performanceHisDataTrashcan = performanceHisDataTrashcanRepository.findById(id);
        ValidationUtil.isNull(performanceHisDataTrashcan, "PerformanceHisDataTrashcan", "id", id);
        return performanceHisDataTrashcanMapper.toDto(performanceHisDataTrashcan.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PerformanceHisDataTrashcanDTO create(PerformanceHisDataTrashcan resources) {
        return performanceHisDataTrashcanMapper.toDto(performanceHisDataTrashcanRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(PerformanceHisDataTrashcan resources) {
        Optional<PerformanceHisDataTrashcan> optionalPerformanceHisDataTrashcan = performanceHisDataTrashcanRepository.findById(resources.getId());
        ValidationUtil.isNull(optionalPerformanceHisDataTrashcan, "PerformanceHisDataTrashcan", "id", resources.getId());

        PerformanceHisDataTrashcan performanceHisDataTrashcan = optionalPerformanceHisDataTrashcan.get();
        // 此处需自己修改
        resources.setId(performanceHisDataTrashcan.getId());
        performanceHisDataTrashcanRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        performanceHisDataTrashcanRepository.deleteById(id);
    }
}