package me.zhengjie.modules.system.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import me.zhengjie.modules.system.domain.AreaCode;
import me.zhengjie.modules.system.repository.AreaCodeRepository;
import me.zhengjie.modules.system.service.AreaCodeService;
import me.zhengjie.modules.system.service.dto.AreaCodeDTO;
import me.zhengjie.modules.system.service.dto.AreaCodeQueryCriteria;
import me.zhengjie.modules.system.service.dto.AreaCodeDTO;
import me.zhengjie.modules.system.service.dto.DeptDTO;
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
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

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
    public List<AreaCodeDTO> queryAll(AreaCodeQueryCriteria criteria) {
        return areaCodeMapper.toDto(areaCodeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    public AreaCodeDTO findById(Long code) {
        Optional<AreaCode> areaCode = areaCodeRepository.findById(code);
        ValidationUtil.isNull(areaCode, "AreaCode", "code", code);
        return areaCodeMapper.toDto(areaCode.get());
    }


    @Override
    public Object buildTree(List<AreaCodeDTO> areaCodeDTOS) {
        Set<AreaCodeDTO> trees = new LinkedHashSet<>();
        Set<AreaCodeDTO> depts= new LinkedHashSet<>();
        List<String> deptNames = areaCodeDTOS.stream().map(AreaCodeDTO::getName).collect(Collectors.toList());
        Boolean isChild;
        for (AreaCodeDTO areaCodeDTO : areaCodeDTOS) {
            isChild = false;
            if ("0".equals(areaCodeDTO.getPcode().toString())) {
                trees.add(areaCodeDTO);
            }
            for (AreaCodeDTO it : areaCodeDTOS) {
                if (it.getPcode().equals(areaCodeDTO.getCode())) {
                    isChild = true;
                    if (areaCodeDTO.getChildren() == null) {
                        areaCodeDTO.setChildren(new ArrayList<AreaCodeDTO>());
                    }
                    areaCodeDTO.getChildren().add(it);
                }
            }
            if(isChild)
                depts.add(areaCodeDTO);
            else if(!deptNames.contains(areaCodeRepository.findNameByCode(areaCodeDTO.getPcode())))
                depts.add(areaCodeDTO);
        }

        if (CollectionUtils.isEmpty(trees)) {
            trees = depts;
        }

        Integer totalElements = areaCodeDTOS!=null?areaCodeDTOS.size():0;

        Map map = new HashMap();
        map.put("totalElements",totalElements);
        map.put("content",CollectionUtils.isEmpty(trees)?areaCodeDTOS:trees);
        return map;
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