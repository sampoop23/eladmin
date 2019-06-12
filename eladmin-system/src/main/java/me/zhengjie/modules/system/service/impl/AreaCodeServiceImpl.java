package me.zhengjie.modules.system.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import me.zhengjie.modules.system.domain.AreaCode;
import me.zhengjie.modules.system.repository.AreaCodeRepository;
import me.zhengjie.modules.system.service.AreaCodeService;
import me.zhengjie.modules.system.service.dto.AreaCodeDTO;
import me.zhengjie.modules.system.service.dto.AreaCodeQueryCriteria;
import me.zhengjie.modules.system.service.mapper.AreaCodeMapper;
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
 * @date -06-12
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AreaCodeServiceImpl implements AreaCodeService {

    @Autowired
    private AreaCodeRepository areaCodeRepository;

    @Autowired
    private AreaCodeMapper areaCodeMapper;

    @Override
    public Object queryAll(AreaCodeQueryCriteria criteria, Pageable pageable) {
        Page<AreaCode> page = areaCodeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(areaCodeMapper::toDto));
    }

    @Override
    public Object queryAll(AreaCodeQueryCriteria criteria) {
        return areaCodeMapper.toDto(areaCodeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    public AreaCodeDTO findById(Long code) {
        Optional<AreaCode> areaCode = areaCodeRepository.findById(code);
        ValidationUtil.isNull(areaCode, "AreaCode", "code", code);
        return areaCodeMapper.toDto(areaCode.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AreaCodeDTO create(AreaCode resources) {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        resources.setCode(snowflake.nextId());
        return areaCodeMapper.toDto(areaCodeRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AreaCode resources) {
        Optional<AreaCode> optionalAreaCode = areaCodeRepository.findById(resources.getCode());
        ValidationUtil.isNull(optionalAreaCode, "AreaCode", "id", resources.getCode());

        AreaCode areaCode = optionalAreaCode.get();
        // 此处需自己修改
        resources.setCode(areaCode.getCode());
        areaCodeRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long code) {
        areaCodeRepository.deleteById(code);
    }
}